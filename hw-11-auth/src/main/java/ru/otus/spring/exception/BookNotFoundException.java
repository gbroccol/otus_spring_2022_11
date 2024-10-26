package ru.otus.spring.exception;

public class BookNotFoundException extends NotFoundException {
    public BookNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}