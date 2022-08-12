package by.bobrovich.telegram.bot.service;

import by.bobrovich.telegram.bot.dto.CurrencyDto;
import by.bobrovich.telegram.bot.dto.PageDtos;
import by.bobrovich.telegram.bot.enums.Currency;
import by.bobrovich.telegram.bot.keyboard.Keyboard;
import by.bobrovich.telegram.bot.keyboard.menu.KeyboardKeyName;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

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
        KeyboardKeyName keyboardKeyName = KeyboardKeyName.of(message.getText());

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
        if (buttons.length < 1) {

        }else {
            operation = Currency.Operation.valueOf(buttons[0]);
            currency = Currency.valueOf(buttons[1]);

            PageDtos<CurrencyDto> currencies = currencyService.getCurrencies(currency, operation);
            convert = conversionService.convert(currencies.getContent(), String.class);
        }

        SendMessage sendMessage = SendMessage.builder()
                .chatId(message.getMessage().getChatId())
                .replyToMessageId(message.getMessage().getMessageId())
                .text(operation != null ? operation.getCondition() + " " + currency.name() + ":\n " + convert : convert)
                .build();

        sendMessage.enableMarkdown(true);

        return sendMessage;
    }
}
