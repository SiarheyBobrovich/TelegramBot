package by.bobrovich.telegram.bot.keyboard.query;

import by.bobrovich.telegram.bot.exceptions.ParsePropertyException;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class NamedInlineKeyboardMarkup extends InlineKeyboardMarkup {
    private final String inlineKeyboardName;

    NamedInlineKeyboardMarkup(String inlineKeyboardName, List<String> keys) {
        this.inlineKeyboardName = inlineKeyboardName;
        final List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>(keys.size());

        keys.forEach(key -> {
            final String[] buttons = key.split(",");
            final List<InlineKeyboardButton> row = new ArrayList<>(buttons.length);

            for (String s : buttons) {
                row.add(getInlineKeyboardButton(s));
            }

            keyboardRows.add(row);
        });

        super.setKeyboard(keyboardRows);
    }

    public String getInlineKeyboardName() {
        return inlineKeyboardName;
    }

    public abstract String getMessage();

    private InlineKeyboardButton getInlineKeyboardButton(String s) {
        String[] split = s.split("=");

        if (split.length != 2) {
            throw new ParsePropertyException("Не верно заполненые поля");
        }

        return InlineKeyboardButton.builder()
                .text(split[0])
                .callbackData(split[1])
                .build();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NamedInlineKeyboardMarkup)) return false;
        if (!super.equals(o)) return false;
        NamedInlineKeyboardMarkup that = (NamedInlineKeyboardMarkup) o;
        return Objects.equals(inlineKeyboardName, that.inlineKeyboardName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), inlineKeyboardName);
    }
}
