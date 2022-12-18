package ru.otus.spring.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class QuestionsDaoCSVTest {

    private QuestionsDaoCSV questionsDaoCSV = new QuestionsDaoCSV("questionsTest.csv");

    @Test
    void findAllCheckResultSize() {

        List<String[]> dataAsList = questionsDaoCSV.getDataAsList();

        Assertions.assertEquals(20, dataAsList.size());
        Assertions.assertEquals(5, dataAsList.stream().map(x -> x[0]).distinct().count());

    }
}