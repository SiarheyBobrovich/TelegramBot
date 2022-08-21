package by.bobrovich.telegram.bot;

import by.bobrovich.telegram.bot.adapter.api.IBotMessagesAdapter;
import by.bobrovich.telegram.bot.utils.YamlPropertySourceFactory;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.concurrent.ExecutorService;

@Service
@ConfigurationProperties(prefix = "yaml")
@PropertySource(value = "classpath:bot.yml", factory = YamlPropertySourceFactory.class)
public class Bot extends TelegramLongPollingBot {

    private final String name;
    private final String token;
    private final IBotMessagesAdapter adapter;
    private final ExecutorService executorService;

    public Bot(@Value("${bot.name}") String name,
               @Value("${bot.token}") String token,
               IBotMessagesAdapter adapter,
               ExecutorService executorService) {
        this.name = name;
        this.token = token;
        this.adapter = adapter;
        this.executorService = executorService;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(final Update update) {
        if (update.hasMessage()) {
            executorService.execute(() -> adapter.sendMsg(update.getMessage(), this));

        }else if (update.hasCallbackQuery()){
            executorService.execute(() -> adapter.sendMsg(update.getCallbackQuery(), this));

        }else {
            LogFactory.getLog(this.getClass()).error(update);
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }
}
