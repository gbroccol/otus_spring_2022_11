package ru.otus.spring.exception;

public class GenreNotFoundException extends NotFoundException {
    public GenreNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}