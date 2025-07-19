package ru.practicum.shareit.exceptions.exception;

public class OwnerNotFoundException extends RuntimeException {
    public OwnerNotFoundException(String message) {
        super(message);
    }
}
