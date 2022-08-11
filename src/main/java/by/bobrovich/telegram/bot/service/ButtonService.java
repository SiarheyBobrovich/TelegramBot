package by.bobrovich.telegram.bot.service;

import by.bobrovich.telegram.bot.core.keyboard.Keyboard;
import by.bobrovich.telegram.bot.core.keyboard.KeyboardName;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Objects;

@Component
public class ButtonService {

    private final Keyboard keyboard;

    public ButtonService(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    void addKeyBoard(final KeyboardName keyboardName, final SendMessage sendMessage) {
        if (!Objects.isNull(keyboardName)) {
            sendMessage.setReplyMarkup(keyboard.getReplyKeyboardMarkup(keyboardName));
        }
    }
}
