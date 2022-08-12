package by.bobrovich.telegram.bot.config;

import by.bobrovich.telegram.bot.keyboard.menu.KeyboardKeyName;
import by.bobrovich.telegram.bot.keyboard.menu.NamedKeyBoardRow;
import by.bobrovich.telegram.bot.keyboard.query.NamedInlineKeyboardMarkup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KeyboardConfig {

    @Bean
    public ReplyKeyboardMarkup replyKeyboardMarkup() {
        return new ReplyKeyboardMarkup();
    }

    @Bean
    Map<KeyboardKeyName, InlineKeyboardMarkup> markupMap(List<NamedInlineKeyboardMarkup> list) {
        final HashMap<KeyboardKeyName, InlineKeyboardMarkup> inlineKeyboardMarkupHashMap = new HashMap<>(list.size());
        list.forEach(x -> inlineKeyboardMarkupHashMap.put(x.getName(), x));

        return inlineKeyboardMarkupHashMap;
    }

    @Bean
    public NamedKeyBoardRow help() {
        NamedKeyBoardRow keyboardRow = new NamedKeyBoardRow(KeyboardKeyName.HELP);
        keyboardRow.add(menuButton());

        return keyboardRow;
    }

    @Bean
    public NamedKeyBoardRow menu() {
        NamedKeyBoardRow keyboardRow = new NamedKeyBoardRow(KeyboardKeyName.MENU);

        keyboardRow.add(currencyButton());
        keyboardRow.add(settingButton());

        return keyboardRow;
    }

    @Bean
    public NamedKeyBoardRow setting() {
        NamedKeyBoardRow keyboardRow = new NamedKeyBoardRow(KeyboardKeyName.SETTING);
        keyboardRow.add(cityButton());
        keyboardRow.add(menuButton());

        return keyboardRow;
    }

    @Bean
    public KeyboardButton helpButton() {
        return new KeyboardButton("help");
    }

    @Bean
    public KeyboardButton menuButton() {
        return new KeyboardButton("menu");
    }

    @Bean
    public KeyboardButton cityButton() {
        return new KeyboardButton("city");
    }

    @Bean
    public KeyboardButton currencyButton() {
        return new KeyboardButton("currency");
    }

    @Bean
    public KeyboardButton settingButton() {
        return new KeyboardButton("setting");
    }
}
