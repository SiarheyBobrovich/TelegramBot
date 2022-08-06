package by.bobrovich.telegram.bot.core.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

@Component
public class Keyboard {
    private final ReplyKeyboardMarkup replyKeyboardMarkup;

    public Keyboard(ReplyKeyboardMarkup replyKeyboardMarkup, List<KeyboardRow> keys) {
        this.replyKeyboardMarkup = replyKeyboardMarkup;
        this.replyKeyboardMarkup.setKeyboard(keys);
        this.replyKeyboardMarkup.setSelective(true);
        this.replyKeyboardMarkup.setResizeKeyboard(true);
        this.replyKeyboardMarkup.setOneTimeKeyboard(false);
    }

    public ReplyKeyboardMarkup getReplyKeyboardMarkup() {
        return replyKeyboardMarkup;
    }
}
