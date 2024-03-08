package ru.otus.spring.exception;

public class AuthorNotExistsException extends Exception {
    public AuthorNotExistsException(String errorMessage) {
        super(errorMessage);
    }
}