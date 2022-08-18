package by.bobrovich.telegram.bot.utils;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SendMessageUtil {
    private SendMessageUtil() {}

    public static SendMessage.SendMessageBuilder getDefaultSendMessageBuilder(long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .parseMode(ParseMode.HTML);
    }
}
