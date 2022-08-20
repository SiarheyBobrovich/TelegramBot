package by.bobrovich.telegram.bot.adapter.api;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface IBotMessagesAdapter {

    void sendMsg(Message message, AbsSender absSender);
    void sendMsg(CallbackQuery query, AbsSender absSender);
}
