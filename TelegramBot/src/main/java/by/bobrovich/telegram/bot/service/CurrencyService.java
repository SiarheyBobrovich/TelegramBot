package by.bobrovich.telegram.bot.service;

import by.bobrovich.telegram.bot.converters.PageDtoToInlineMarkupConverter;
import by.bobrovich.telegram.bot.dto.CurrencyDto;
import by.bobrovich.telegram.bot.dto.PageDtos;
import by.bobrovich.telegram.bot.dto.user.User;
import by.bobrovich.telegram.bot.enums.Currency;
import by.bobrovich.telegram.bot.exceptions.LoadUserException;
import by.bobrovich.telegram.bot.service.api.ICurrencyService;
import by.bobrovich.telegram.bot.service.api.IUserService;
import by.bobrovich.telegram.bot.utils.YamlPropertySourceFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Service
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
public class CurrencyService implements ICurrencyService {
    private final String uri;
    private final RestTemplate template;
    private final ObjectMapper mapper;
    private final ConversionService conversionService;
    private final PageDtoToInlineMarkupConverter markupConverter;
    private final IUserService userService;

    public CurrencyService(@Value("${currency.service.uri}") String uri,
                           RestTemplate template,
                           ObjectMapper mapper,
                           ConversionService conversionService,
                           PageDtoToInlineMarkupConverter markupConverter,
                           IUserService userService) {
        this.uri = uri;
        this.template = template;
        this.mapper = mapper;
        this.conversionService = conversionService;
        this.markupConverter = markupConverter;
        this.userService = userService;
    }

    @Override
    public void sendMsg(long chatId, AbsSender absSender, String... arg) {
        final SendMessage.SendMessageBuilder messageBuilder = SendMessage.builder()
                .chatId(chatId)
                .parseMode(ParseMode.HTML);

        final User user;
        final String convert;
        final Currency.Operation operation;
        final Currency currency;

        try {
            user = userService.load(chatId);

        }catch (LoadUserException e) {
            messageBuilder.text(e.getMessage());
            execute(absSender, messageBuilder.build());
            return;
        }

        operation = Currency.Operation.valueOf(arg[1]);
        currency = Currency.valueOf(arg[2]);
        final PageDtos<CurrencyDto> currencies;

        try {
            if (arg.length == 6) {
                currencies = getCurrenciesPageable(
                        currency, operation, arg[4], Long.parseLong(arg[3]), Integer.parseInt(arg[5])
                );

                messageBuilder.replyMarkup(markupConverter.convert(
                        currencies, currency, operation, arg[4], arg[5])
                );

            } else {
                currencies = getCurrenciesPageable(
                        currency, operation, user.getCity(), 0L, user.getSize()
                );

                messageBuilder.replyMarkup(markupConverter.convert(
                        currencies, currency, operation, user.getCity(), String.valueOf(user.getSize()))
                );
            }

            convert = conversionService.convert(currencies.getContent(), String.class);

            messageBuilder.text(currency.name() + " " + operation.getCondition() + ":\n" + convert);


        }catch (RestClientException e) {
            messageBuilder.text("Размер изменён, обновите запрос");
        }

        execute(absSender, messageBuilder.build());
    }

    public PageDtos<CurrencyDto> getCurrenciesPageable(Currency currency, Currency.Operation operation, String city, long page, int size) {
        return mapper.convertValue(
                template.getForObject(uri + currency.name() + "/" + operation.name() + "/" + city +
                        "?page=" + page + "&size=" + size, PageDtos.class), new TypeReference<>() {}
        );
    }
}
