package by.bobrovich.telegram.bot.service;

import by.bobrovich.telegram.bot.converters.PageDtoToInlineMarkupConverter;
import by.bobrovich.telegram.bot.dto.CurrencyDto;
import by.bobrovich.telegram.bot.dto.PageDtos;
import by.bobrovich.telegram.bot.dto.user.User;
import by.bobrovich.telegram.bot.enums.Currency;
import by.bobrovich.telegram.bot.exceptions.LoadUserException;
import by.bobrovich.telegram.bot.exceptions.SaveUserException;
import by.bobrovich.telegram.bot.keyboard.KeyboardsContainer;
import by.bobrovich.telegram.bot.service.api.IBotService;
import by.bobrovich.telegram.bot.service.api.IUserService;
import by.bobrovich.telegram.bot.utils.SendMessageUtil;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class BotService implements IBotService {

    private final KeyboardsContainer keyboardsContainer;
    private final ConversionService conversionService;
    private final CurrencyService currencyService;
    private final PageDtoToInlineMarkupConverter markupConverter;
    private final IUserService userService;

    public BotService(KeyboardsContainer keyboardsContainer,
                      ConversionService conversionService,
                      CurrencyService currencyService,
                      PageDtoToInlineMarkupConverter markupConverter,
                      IUserService userService) {

        this.keyboardsContainer = keyboardsContainer;
        this.conversionService = conversionService;
        this.currencyService = currencyService;
        this.markupConverter = markupConverter;
        this.userService = userService;
    }

    @Override
    public SendMessage sendMsg(Message message) {
        final Long chatId = message.getChatId();
        final User user;

        try {
            user = userService.load(chatId);

        }catch (LoadUserException e) {
            return SendMessage.builder()
                    .text(e.getMessage())
                    .chatId(chatId)
                    .build();
        }

        final String textFromUser = message.getText();

        SendMessage sendMessage = SendMessage.builder()
                .chatId(message.getChatId())
                .replyMarkup(keyboardsContainer.getReplyKeyboardMarkup(textFromUser))
                .text(keyboardsContainer.getMessage(textFromUser))
                .build();

        sendMessage.enableMarkdown(true);

        return sendMessage;
    }

    @Override
    public SendMessage sendMsg(CallbackQuery message) {
        final String[] buttons = message.getData().split(" ");
        final String convert;
        final Currency.Operation operation;
        final Currency currency;

        final User user;
        final Long chatId = message.getMessage().getChatId();

        final SendMessage.SendMessageBuilder builder = SendMessageUtil.getDefaultSendMessageBuilder(
                chatId);

        try {
            user = userService.load(chatId);

        }catch (LoadUserException e) {
            return builder.text(e.getMessage())
                    .build();
        }

        if ("CITY".equals(buttons[0]) && buttons.length == 2) {
            try {
                userService.updateCity(user, buttons[1]);
                builder.text("Город сохранён успешно");

            }catch (SaveUserException e) {
                builder.text("Сервис временно недоступен.");
            }

        } else if ("CURRENCY".equals(buttons[0])) {
            operation = Currency.Operation.valueOf(buttons[1]);
            currency = Currency.valueOf(buttons[2]);
            PageDtos<CurrencyDto> currencies;

            if (buttons.length == 4) {
                currencies = currencyService.getCurrenciesPageable(currency, operation, Long.parseLong(buttons[3]), user.getSize());
            }else {
                currencies = currencyService.getCurrenciesPageable(currency, operation, 0L, user.getSize());
            }

            convert = conversionService.convert(currencies.getContent(), String.class);

            builder.text(currency.name() + " " + operation.getCondition() + ":\n" + convert);
            builder.replyMarkup(markupConverter.convert(currencies, currency, operation));

        } else if ("SIZE".equals(buttons[0]) && buttons.length == 2 && buttons[1].matches("^\\d++")) {
            try {
                userService.updateSize(user, Integer.parseInt(buttons[1]));
                builder.text("Размер страницы обновлён до " + buttons[1]);

            }catch (SaveUserException e) {
                builder.text("Сервис временно недоступен.");
            }

        }else {
            builder.text("Операция не поддерживается");
        }

        return builder.build();
    }
}
