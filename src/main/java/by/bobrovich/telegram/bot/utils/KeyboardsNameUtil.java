package by.bobrovich.telegram.bot.utils;

import by.bobrovich.telegram.bot.core.keyboard.KeyboardName;

public class KeyboardsNameUtil {

    private KeyboardsNameUtil() {}

    public static KeyboardName getKeyboardsName(String key) {

        for (KeyboardName value : KeyboardName.values()) {
            if (value.name().equalsIgnoreCase(key.replaceAll("[^\\p{Alpha}]", ""))) {
                return value;
            }
        }

        return KeyboardName.HELP;
    }
}
