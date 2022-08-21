package by.bobrovich.finance_service.exceptions;

public class ParseException extends RuntimeException{

    public ParseException(Throwable cause) {
        super(cause);
    }

    public ParseException(String message) {
        super(message);
    }
}
