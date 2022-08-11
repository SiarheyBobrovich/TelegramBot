package by.bobrovich.telegram.bot.core.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;

@Component
public class Keyboard {

    private final ReplyKeyboardMarkup replyKeyboardMarkup;
    private final List<KeyBoardNamingRow> keys;

    public Keyboard(ReplyKeyboardMarkup replyKeyboardMarkup, List<KeyBoardNamingRow> keys) {
        this.keys = keys;

        this.replyKeyboardMarkup = replyKeyboardMarkup;
        this.replyKeyboardMarkup.setSelective(true);
        this.replyKeyboardMarkup.setResizeKeyboard(true);
        this.replyKeyboardMarkup.setOneTimeKeyboard(false);
    }

    public ReplyKeyboardMarkup getReplyKeyboardMarkup(KeyboardName name) {
        for (KeyBoardNamingRow key : keys) {

            if (name.equals(key.getName())) {
                replyKeyboardMarkup.setKeyboard(List.of(key));
            }
        }

        return replyKeyboardMarkup;
    }
}
