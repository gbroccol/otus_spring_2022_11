package ru.otus.spring.service.impl;

import ru.otus.spring.exception.QuestionsReadingException;

import java.util.List;

public interface ServiceCRUD<T> {
   List<T> findAll() throws QuestionsReadingException;
}
