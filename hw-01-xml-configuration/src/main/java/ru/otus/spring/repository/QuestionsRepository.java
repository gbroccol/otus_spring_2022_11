package ru.otus.spring.repository;

import ru.otus.spring.model.Question;

import java.util.Collection;

public interface QuestionsRepository {
    Collection<Question> findAll();
}
