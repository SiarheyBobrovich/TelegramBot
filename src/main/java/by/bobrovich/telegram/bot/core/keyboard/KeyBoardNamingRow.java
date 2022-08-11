package by.bobrovich.telegram.bot.core.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Objects;

public class KeyBoardNamingRow extends KeyboardRow {

    private final KeyboardName name;

    public KeyBoardNamingRow(KeyboardName name) {
        this.name = name;
    }

    public KeyboardName getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeyBoardNamingRow)) return false;
        if (!super.equals(o)) return false;
        KeyBoardNamingRow that = (KeyBoardNamingRow) o;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
