package by.it_academy.user.controllers.handlers;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice
public class GlobalHandler {

    private final String logref = "logref";
    private final String message = "message";

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handle(RuntimeException exception) {
        return Map.of(
                logref, "error",
                message, "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору"
        );
    }
@ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handle(NotYetImplementedException exception) {
        return Map.of(
                logref, "error",
                message, exception.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(EntityExistsException exception) {
        return Map.of(
                logref, "error",
                message, exception.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handle(OptimisticLockException exception) {
        return Map.of(
                logref, "error",
                message, exception.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handle(EntityNotFoundException exception) {
        return Map.of(
                logref, "error",
                message, "Не найдена"
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> handle(SecurityException exception) {
        return Map.of(
                logref, "error",
                message, exception.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(HttpMessageNotReadableException exception) {
        return Map.of(
                logref, "error",
                message, "Запрос не корректен, проверьте запрос"
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(ConstraintViolationException exception) {
        final Map<String, Object> map = new HashMap<>();
        map.put(logref, "structured_error");

        final List<Map<String, Object>> errors = new ArrayList<>();

        exception.getConstraintViolations().forEach(x ->  {

            final String path = x.getPropertyPath().toString();
            final int pointIndex = x.getPropertyPath().toString().lastIndexOf(".");

            errors.add(Map.of(
                    "field", path.substring(pointIndex + 1),
                    message, x.getMessage()
            ));
        });

        map.put("errors", errors);

        return map;
    }
}
