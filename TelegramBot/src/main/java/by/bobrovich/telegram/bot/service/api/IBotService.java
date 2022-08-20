package by.bobrovich.telegram.bot.service.api;

import org.apache.juli.logging.LogFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

public interface IBotService {
    void sendMsg(long chatId, AbsSender bot, String... arg);

    default <T extends Serializable, M extends BotApiMethod<T>> void execute(AbsSender absSender, M method) {

        try {
            absSender.execute(method);

        }catch (TelegramApiException e) {
            LogFactory.getLog(this.getClass()).error(e);
        }
    }
}
