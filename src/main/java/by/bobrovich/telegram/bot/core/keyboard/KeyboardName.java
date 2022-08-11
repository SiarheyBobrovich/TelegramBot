package by.bobrovich.telegram.bot.core.keyboard;

public enum KeyboardName {

    HELP("Выберите команду"),
    SETTING("Что будем настаивать?"),

    CITY("Выбирите город.");

    private final String message;

    KeyboardName(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static KeyboardName of(String str) {
        return valueOf(str.replaceAll("[^\\p{Alpha}]", "").toUpperCase());
    }
}
