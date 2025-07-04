package ru.practicum.shareit.user.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.user.User;

import java.util.Map;

@RestControllerAdvice
public class UserExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(UserExceptionHandler.class);

    @ExceptionHandler(UserInvalidException.class)
    public ResponseEntity<Map<String, User>> handleEmailAlreadyExists(UserInvalidException ex) {
        Map<String, User> error = Map.of(ex.getMessage(), ex.getUser());
        log.error("UserInvalidException: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserInvalidEmailNotFoundException.class)
    public ResponseEntity<Map<String, User>> handleEmailNotFound(UserInvalidEmailNotFoundException ex) {
        Map<String, User> error = Map.of(ex.getMessage(), ex.getUser());
        log.error("UserInvalidEmailNotFoundException: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Map<String, String>> handleThrowable(Throwable ex) {
        log.error("Throwable: {}", ex.getMessage(), ex);
        Map<String, String> error = Map.of("Internal server error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
