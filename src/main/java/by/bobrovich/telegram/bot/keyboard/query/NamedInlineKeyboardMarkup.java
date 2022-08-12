package by.bobrovich.telegram.bot.keyboard.query;

import by.bobrovich.telegram.bot.keyboard.menu.KeyboardKeyName;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class NamedInlineKeyboardMarkup extends InlineKeyboardMarkup {
    private final KeyboardKeyName name;

    NamedInlineKeyboardMarkup(KeyboardKeyName name, List<String> cities) {
        this.name = name;

        List<List<InlineKeyboardButton>> keyList = new ArrayList<>();

        List<InlineKeyboardButton> buttonList = cities.stream()
                .map(x -> {
                            String[] split = x.trim().split("=");
                            return InlineKeyboardButton.builder()
                                    .callbackData(split[0])
                                    .text(split[1])
                                    .build();
                })
                .collect(Collectors.toList());

        List<InlineKeyboardButton> list = new ArrayList<>();

        int index = 0;
        for (InlineKeyboardButton inlineKeyboardButton : buttonList) {
            list.add(inlineKeyboardButton);

            if (index % 2 != 0 || buttonList.size() < 2) {
                keyList.add(list);
                list = new ArrayList<>();
            }

            index++;
        }

        super.setKeyboard(keyList);
    }

    public KeyboardKeyName getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NamedInlineKeyboardMarkup)) return false;
        if (!super.equals(o)) return false;
        NamedInlineKeyboardMarkup that = (NamedInlineKeyboardMarkup) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
