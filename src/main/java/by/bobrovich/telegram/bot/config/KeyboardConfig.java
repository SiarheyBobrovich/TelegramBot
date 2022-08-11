package by.bobrovich.telegram.bot.config;

import by.bobrovich.telegram.bot.core.keyboard.KeyBoardNamingRow;
import by.bobrovich.telegram.bot.core.keyboard.KeyboardName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

@Configuration
public class KeyboardConfig {

    @Bean
    public ReplyKeyboardMarkup replyKeyboardMarkup() {
        return new ReplyKeyboardMarkup();
    }

    @Bean
    public KeyBoardNamingRow settingRow() {
        KeyBoardNamingRow keyboardRow = new KeyBoardNamingRow(KeyboardName.SETTING);

        keyboardRow.add(minsk());
        keyboardRow.add(brest());

        return keyboardRow;
    }

    @Bean
    public KeyBoardNamingRow helpRow() {
        KeyBoardNamingRow keyboardRow = new KeyBoardNamingRow(KeyboardName.HELP);
        keyboardRow.add(settingButton());

        return keyboardRow;
    }

    @Bean
    public KeyboardButton helpButton() {
        return new KeyboardButton("/help");
    }

    @Bean
    public KeyboardButton settingButton() {
        return new KeyboardButton("/setting");
    }

    @Bean
    public KeyboardButton minsk() {
        return new KeyboardButton("/Minsk");
    }

    @Bean
    public KeyboardButton brest() {
        return new KeyboardButton("/Brest");
    }
}
