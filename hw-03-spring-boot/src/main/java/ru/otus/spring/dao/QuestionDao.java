package ru.otus.spring.dao;

import ru.otus.spring.exception.QuestionsReadingException;

import java.util.List;

public interface QuestionDao {
    List<String[]> getDataAsList() throws QuestionsReadingException;
}
