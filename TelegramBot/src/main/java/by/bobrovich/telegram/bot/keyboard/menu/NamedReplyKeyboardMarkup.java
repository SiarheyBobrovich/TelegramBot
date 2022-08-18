package by.bobrovich.telegram.bot.keyboard.menu;

import by.bobrovich.telegram.bot.keyboard.api.INamedKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class NamedReplyKeyboardMarkup extends ReplyKeyboardMarkup {
    private final String keyboardName;

    NamedReplyKeyboardMarkup(String keyboardName, List<String> keys) {
        this.keyboardName = keyboardName;
        final List<KeyboardRow> keyboardRows = new ArrayList<>(keys.size());

        keys.forEach(key -> {
            final String[] buttons = key.split(",");
            final KeyboardRow row = new KeyboardRow(buttons.length);

            for (String s : buttons) {
                row.add(KeyboardButton.builder()
                        .text(s)
                        .build()
                );
            }

            keyboardRows.add(row);
        });

        super.setResizeKeyboard(true);
        super.setKeyboard(keyboardRows);
    }

    public String getKeyboardName() {
        return keyboardName;
    }

    public abstract String getMessage();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NamedReplyKeyboardMarkup)) return false;
        if (!super.equals(o)) return false;
        NamedReplyKeyboardMarkup that = (NamedReplyKeyboardMarkup) o;
        return Objects.equals(keyboardName, that.keyboardName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), keyboardName);
    }
}
