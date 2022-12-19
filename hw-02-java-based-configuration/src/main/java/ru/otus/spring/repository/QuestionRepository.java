package ru.otus.spring.repository;

import ru.otus.spring.model.Question;

import java.util.List;

public interface QuestionRepository {
    List<Question> findAll();
}
