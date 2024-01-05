package ru.otus.spring.exception;

public class QuestionsReadingException extends Exception{
    public QuestionsReadingException(String errorMessage) {
        super(errorMessage);
    }
}
