package by.bobrovich.telegram.bot.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@PropertySource("bot.yaml")
public class Bot extends TelegramLongPollingBot {

    private final String name;
    private final String token;

    private final MessageSender messageSender;

    public Bot(@Value("bot.name") String name,
               @Value("bot.token") String token,
               MessageSender messageSender) {
        this.name = name;
        this.token = token;
        this.messageSender = messageSender;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendMessage sendMsg;
        if (message != null && message.hasText()) {
            switch (message.getText()) {

                case "/help":
                    sendMsg = messageSender.sendMsg(message, "Чем могу помочь?");
                    break;

                case "/setting":
                    sendMsg = messageSender.sendMsg(message, "Что будем настраивать?");
                    break;

                default:
                    sendMsg = null;
            }

            try {
                execute(sendMsg);

            } catch (TelegramApiException e) {
                System.err.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }
}
