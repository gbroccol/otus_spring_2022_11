package ru.otus.spring.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.spring.model.Question;

import java.util.Collection;

class QuestionsDaoCSVTest {

    private QuestionsDaoCSV questionsDaoCSV = new QuestionsDaoCSV("questionsTest.csv");

    @Test
    void findAllCheckResultSize() {
        Collection<Question> result = questionsDaoCSV.findAll();
        Assertions.assertEquals(5, result.size());
    }
}