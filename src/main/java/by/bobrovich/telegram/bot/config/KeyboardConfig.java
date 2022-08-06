package by.bobrovich.telegram.bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

@Configuration
public class KeyboardConfig {

    @Bean
    public ReplyKeyboardMarkup replyKeyboardMarkup() {
        return new ReplyKeyboardMarkup();
    }

    @Bean
    public KeyboardRow firstKeyboardRow(List<KeyboardButton> buttons) {
        KeyboardRow keyboardRow = new KeyboardRow();

        keyboardRow.addAll(buttons);

        return keyboardRow;
    }

    @Bean
    public KeyboardButton helpButton() {
        return new KeyboardButton("/help");
    }

    @Bean
    public KeyboardButton settingButtom() {
        return new KeyboardButton("/setting");
    }
}
