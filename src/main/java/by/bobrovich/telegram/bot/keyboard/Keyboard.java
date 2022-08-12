package by.bobrovich.telegram.bot.keyboard;

import by.bobrovich.telegram.bot.keyboard.menu.NamedKeyBoardRow;
import by.bobrovich.telegram.bot.keyboard.menu.KeyboardKeyName;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;
import java.util.Map;

@Component
public class Keyboard {

    private final ReplyKeyboardMarkup replyKeyboardMarkup;
    private final Map<KeyboardKeyName, InlineKeyboardMarkup> inlineKeyboardMarkups;
    private final List<NamedKeyBoardRow> keys;

    public Keyboard(ReplyKeyboardMarkup replyKeyboardMarkup,
                    Map<KeyboardKeyName, InlineKeyboardMarkup> inlineKeyboardMarkups,
                    List<NamedKeyBoardRow> keys) {

        this.keys = keys;

        this.inlineKeyboardMarkups = inlineKeyboardMarkups;
        this.replyKeyboardMarkup = replyKeyboardMarkup;
        this.replyKeyboardMarkup.setSelective(true);
        this.replyKeyboardMarkup.setResizeKeyboard(true);
        this.replyKeyboardMarkup.setOneTimeKeyboard(false);
    }

    public ReplyKeyboard getReplyKeyboardMarkup(KeyboardKeyName name) {

        if (inlineKeyboardMarkups.containsKey(name)) {
            return inlineKeyboardMarkups.get(name);
        }

        for (NamedKeyBoardRow key : keys) {

            if (name.equals(key.getName())) {
                replyKeyboardMarkup.setKeyboard(List.of(key));
            }
        }

        return replyKeyboardMarkup;
    }
}
