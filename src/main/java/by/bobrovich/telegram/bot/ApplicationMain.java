package by.bobrovich.telegram.bot;

import by.bobrovich.telegram.bot.core.Bot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class ApplicationMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("by.bobrovich.telegram.bot");
        context.refresh();

        Bot bot = context.getBean(Bot.class);
        TelegramBotsApi telegramBotsApi;
        BotSession botSession;

        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            botSession = telegramBotsApi.registerBot(bot);

        }catch (TelegramApiException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }
}
