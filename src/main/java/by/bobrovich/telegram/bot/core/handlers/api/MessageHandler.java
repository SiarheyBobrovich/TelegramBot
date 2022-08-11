package by.bobrovich.telegram.bot.core.handlers.api;

import by.bobrovich.telegram.bot.core.api.IMessageSender;

public interface MessageHandler {

    boolean isExist(String message);

    IMessageSender getMessageSender();
}
