package by.bobrovich.telegram.bot.core.keyboard.menu;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Objects;

public class NamedKeyBoardRow extends KeyboardRow {

    private final KeyboardKeyName name;

    public NamedKeyBoardRow(KeyboardKeyName name) {
        this.name = name;
    }

    public KeyboardKeyName getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NamedKeyBoardRow)) return false;
        if (!super.equals(o)) return false;
        NamedKeyBoardRow that = (NamedKeyBoardRow) o;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
