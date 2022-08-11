package by.bobrovich.telegram.bot.service;

import by.bobrovich.telegram.bot.core.keyboard.Keyboard;
import by.bobrovich.telegram.bot.core.keyboard.KeyboardName;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

@Service
public class BotService {

    private final Keyboard keyboard;
    private final Map<KeyboardName, String> messages;
    public BotService(Keyboard keyboard, Map<KeyboardName, String> messages) {
        this.keyboard = keyboard;
        this.messages = messages;
    }

    public SendMessage sendMsg(Message message) {
        KeyboardName keyboardName = KeyboardName.of(message.getText());

        SendMessage sendMessage = SendMessage.builder()
                .chatId(message.getChatId())
                .replyToMessageId(message.getMessageId())
                .replyMarkup(keyboard.getReplyKeyboardMarkup(keyboardName))
                .text(messages.get(keyboardName))
                .build();

        sendMessage.enableMarkdown(true);

        return sendMessage;
    }
}
