package ru.otus.spring.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("CSV reader ")
class QuestionDaoCSVTest {

    private QuestionDaoCSV questionDaoCSV;

    @BeforeEach
    void setUp() {
        questionDaoCSV = new QuestionDaoCSV("questionsTest.csv");
    }

    @Test
    @DisplayName("читает все строки из csv файла кроме заголовка")
    void findAllCheckResultSize() {

        List<String[]> dataAsList = questionDaoCSV.getDataAsList();
        Assertions.assertEquals(20, dataAsList.size());
    }
}