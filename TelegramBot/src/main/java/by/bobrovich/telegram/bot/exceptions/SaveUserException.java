package by.bobrovich.telegram.bot.exceptions;

import org.springframework.http.HttpStatus;

public class SaveUserException extends Exception {

    public SaveUserException(HttpStatus statusCode) {
        super(statusCode.toString());
    }
}
