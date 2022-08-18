package by.bobrovich.telegram.bot.service.api;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface IBotService {

    SendMessage sendMsg(Message message);
    SendMessage sendMsg(CallbackQuery message);
}
