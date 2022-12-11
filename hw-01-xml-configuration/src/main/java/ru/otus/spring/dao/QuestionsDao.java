package ru.otus.spring.dao;

import ru.otus.spring.model.Question;

import java.io.IOException;
import java.util.Collection;

public interface QuestionsDao {
    Collection<Question> findAll() throws IOException;
}
