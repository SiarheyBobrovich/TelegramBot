package by.bobrovich.telegram.bot.keyboard.api;


import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface INamedKeyboard {
    String getKeyboardName();
    ReplyKeyboard getReplyKeyboard();
    String getMessage();
}
