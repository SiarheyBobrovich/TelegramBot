package by.bobrovich.telegram.bot.exceptions;

import org.springframework.http.HttpStatus;

public class LoadUserException extends Exception {

    public LoadUserException(String message) {
        super(message);
    }
}
