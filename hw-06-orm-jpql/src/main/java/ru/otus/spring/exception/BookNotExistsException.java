package ru.otus.spring.exception;

public class BookNotExistsException extends Exception {
    public BookNotExistsException(String errorMessage) {
        super(errorMessage);
    }
}