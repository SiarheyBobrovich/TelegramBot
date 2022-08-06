package by.bobrovich.telegram.bot.core.api;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface IMessageSender {

    SendMessage sendMsg(Message message, String question);
}
