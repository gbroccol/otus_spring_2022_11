package ru.otus.spring.exception;

public class AuthorNotFoundException extends NotFoundException {
    public AuthorNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}