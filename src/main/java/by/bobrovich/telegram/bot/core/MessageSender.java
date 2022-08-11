package by.bobrovich.telegram.bot.core;

import by.bobrovich.telegram.bot.core.api.IMessageSender;
import by.bobrovich.telegram.bot.core.keyboard.Keyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MessageSender implements IMessageSender {

    private final Keyboard keyboard;

    public MessageSender(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public SendMessage sendMsg(Message message, String text) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(message.getChatId())
                .replyToMessageId(message.getMessageId())
                .text(text)
                .build();

        sendMessage.enableMarkdown(true);

        return sendMessage;
    }
}
