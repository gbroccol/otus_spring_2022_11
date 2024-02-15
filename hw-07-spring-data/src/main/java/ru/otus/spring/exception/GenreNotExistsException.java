package ru.otus.spring.exception;

public class GenreNotExistsException extends Exception {
    public GenreNotExistsException(String errorMessage) {
        super(errorMessage);
    }
}