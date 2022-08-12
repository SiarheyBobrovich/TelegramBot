package by.bobrovich.telegram.bot.core.keyboard.menu;

public enum KeyboardKeyName {

    HELP("Выберите команду"),
    MENU("Главное меню"),
    CURRENCY("Выбирете валюту: "),
    SETTING("Что будем настраивать?"),
    CITY("Выберите город:");

    private final String message;

    KeyboardKeyName(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static KeyboardKeyName of(String str) {
        return valueOf(str.toUpperCase());
    }
}
