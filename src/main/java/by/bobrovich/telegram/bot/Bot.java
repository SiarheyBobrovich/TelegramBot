package by.bobrovich.telegram.bot;

import by.bobrovich.telegram.bot.service.BotService;
import by.bobrovich.telegram.bot.utils.YamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@ConfigurationProperties(prefix = "yaml")
@PropertySource(value = "classpath:bot.yml", factory = YamlPropertySourceFactory.class)
public class Bot extends TelegramLongPollingBot {

    private final String name;
    private final String token;
    private final BotService service;

    public Bot(@Value("${bot.name}") String name,
               @Value("${bot.token}") String token,
               BotService service) {
        this.name = name;
        this.token = token;
        this.service = service;
        this.getOptions().setMaxThreads(6);
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        final SendMessage sendMsg;

        if (message != null && message.hasText()) {
            sendMsg = service.sendMsg(message);

        }else if (update.hasCallbackQuery()){
            sendMsg = service.sendMsg(update.getCallbackQuery());

        }else {
            sendMsg = null;
        }

        try {
            execute(sendMsg);

        } catch (TelegramApiException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }
}
