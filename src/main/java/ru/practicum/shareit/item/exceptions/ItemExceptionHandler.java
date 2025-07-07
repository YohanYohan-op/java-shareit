package ru.practicum.shareit.item.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.item.Item;

import java.util.Map;

@RestControllerAdvice
public class ItemExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ItemExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        log.error("UserNotFoundException: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("IllegalArgumentException: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Map<String, Item>> handleItemNotFoundException(ItemNotFoundException ex) {
        Map<String, Item> error = Map.of("Item not found", ex.getItem());
        log.error("ItemNotFoundException: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Map<String, String>> handleThrowable(Throwable ex) {
        log.error("Throwable: {}", ex.getMessage(), ex);
        Map<String, String> error = Map.of("Internal server error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
