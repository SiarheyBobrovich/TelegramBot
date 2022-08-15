package by.bobrovich.telegram.bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class ApplicationConfig {

    @Bean
    public BotSession botSession(TelegramLongPollingBot bot) {
        BotSession botSession;

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            botSession = telegramBotsApi.registerBot(bot);

        }catch (TelegramApiException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getLocalizedMessage());

            throw new RuntimeException(e);
        }

        return botSession;
    }


}
