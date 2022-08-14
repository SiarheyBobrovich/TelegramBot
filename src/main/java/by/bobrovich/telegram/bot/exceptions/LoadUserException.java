package by.bobrovich.telegram.bot.exceptions;

import org.springframework.http.HttpStatus;

public class LoadUserException extends Exception {

    public LoadUserException(HttpStatus statusCode) {
        super(statusCode.toString());
    }
}
