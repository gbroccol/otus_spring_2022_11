package ru.otus.spring.exception;

public class AnswerOutOfBoundException extends Exception {
    public AnswerOutOfBoundException(String errorMessage) {
        super(errorMessage);
    }
}
