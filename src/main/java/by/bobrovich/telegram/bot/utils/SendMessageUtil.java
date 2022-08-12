package by.bobrovich.telegram.bot.utils;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SendMessageUtil {
    private SendMessageUtil() {}

    public static SendMessage.SendMessageBuilder getDefaultSendMessageBuilder(long chatId, int messageId) {
        return SendMessage.builder()
                .chatId(chatId)
                .replyToMessageId(messageId)
                .parseMode(ParseMode.HTML);
    }
}
