package by.bobrovich.telegram.bot.keyboard;

import by.bobrovich.telegram.bot.keyboard.api.INamedKeyboard;
import by.bobrovich.telegram.bot.keyboard.menu.NamedReplyKeyboardMarkup;
import by.bobrovich.telegram.bot.keyboard.query.NamedInlineKeyboardMarkup;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class KeyboardsContainer {

    private final Map<String, NamedReplyKeyboardMarkup> namedKeyboards;
    private final Map<String, NamedInlineKeyboardMarkup> inlineKeyboards;

    public KeyboardsContainer(List<NamedReplyKeyboardMarkup> namedKeyboards,
                              List<NamedInlineKeyboardMarkup> inlineKeyboards) {
        this.namedKeyboards = new HashMap<>();
        this.inlineKeyboards = new HashMap<>();
        namedKeyboards.forEach(x -> this.namedKeyboards.put(x.getKeyboardName(), x));
        inlineKeyboards.forEach(x -> this.inlineKeyboards.put(x.getInlineKeyboardName(), x));
    }

    public ReplyKeyboard getReplyKeyboardMarkup(String keyboardName) {
        final ReplyKeyboard replyKeyboard;

        if (namedKeyboards.containsKey(keyboardName)) {
            replyKeyboard = namedKeyboards.get(keyboardName);

        }else if (inlineKeyboards.containsKey(keyboardName)) {
            replyKeyboard = inlineKeyboards.get(keyboardName);
        }else {
            replyKeyboard = namedKeyboards.get("Меню");
        }

        return replyKeyboard;
    }

    public String getMessage(String keyboardName) {
        if (namedKeyboards.containsKey(keyboardName)) {
            return namedKeyboards.get(keyboardName).getMessage();

        } else if (inlineKeyboards.containsKey(keyboardName)) {
            return inlineKeyboards.get(keyboardName).getMessage();
        }

        return namedKeyboards.get("Меню").getMessage();
    }
}
