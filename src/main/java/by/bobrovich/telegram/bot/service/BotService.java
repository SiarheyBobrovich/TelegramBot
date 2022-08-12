package by.bobrovich.telegram.bot.service;

import by.bobrovich.telegram.bot.dto.CurrencyDto;
import by.bobrovich.telegram.bot.dto.PageDtos;
import by.bobrovich.telegram.bot.enums.Currency;
import by.bobrovich.telegram.bot.keyboard.Keyboard;
import by.bobrovich.telegram.bot.keyboard.menu.KeyboardKeyName;
import by.bobrovich.telegram.bot.utils.SendMessageUtil;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class BotService {

    private final Keyboard keyboard;
    private final Map<KeyboardKeyName, String> messages;
    private final ConversionService conversionService;
    private final CurrencyService currencyService;


    public BotService(Keyboard keyboard, Map<KeyboardKeyName, String> messages, ConversionService conversionService, CurrencyService currencyService) {
        this.keyboard = keyboard;
        this.messages = messages;
        this.conversionService = conversionService;
        this.currencyService = currencyService;
    }

    public SendMessage sendMsg(Message message) {
        String textFromUser = message.getText();

        KeyboardKeyName keyboardKeyName;

        try {
            keyboardKeyName = KeyboardKeyName.of(textFromUser);

        }catch (IllegalArgumentException e) {
            keyboardKeyName = KeyboardKeyName.HELP;
        }

        SendMessage sendMessage = SendMessage.builder()
                .chatId(message.getChatId())
                .replyToMessageId(message.getMessageId())
                .replyMarkup(keyboard.getReplyKeyboardMarkup(keyboardKeyName))
                .text(messages.get(keyboardKeyName))
                .build();

        sendMessage.enableMarkdown(true);

        return sendMessage;
    }

    public SendMessage sendMsg(CallbackQuery message) {
        String[] buttons = message.getData().split(" ");
        String convert = null;
        Currency.Operation operation = null;
        Currency currency = null;

        SendMessage.SendMessageBuilder builder = SendMessageUtil.getDefaultSendMessageBuilder(
                message.getMessage().getChatId(), message.getMessage().getMessageId()
        );

        try {
            operation = Currency.Operation.valueOf(buttons[0]);
            currency = Currency.valueOf(buttons[1]);

            PageDtos<CurrencyDto> currencies = currencyService.getCurrencies(currency, operation);
            convert = conversionService.convert(currencies.getContent(), String.class);

            builder.text(operation.getCondition() + " " + currency.name() + ":\n " + convert);

        }catch (IllegalArgumentException ignore) {
            //add UserService
            builder.text("Успешно!");
        }

        return builder.build();
    }
}
