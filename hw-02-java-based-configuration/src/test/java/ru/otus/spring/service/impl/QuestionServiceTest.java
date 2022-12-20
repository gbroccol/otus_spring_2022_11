package ru.otus.spring.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.dao.QuestionDaoCSV;
import ru.otus.spring.model.Question;
import ru.otus.spring.repository.QuestionRepository;
import ru.otus.spring.repository.QuestionRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

@DisplayName("Реестр вовросов ")
class QuestionServiceTest {

    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        QuestionDao questionDao = new QuestionDaoCSV("questionsTest.csv");
        QuestionRepository questionRepository = new QuestionRepositoryImpl(questionDao);
        questionService = new QuestionService(questionRepository);
    }

    @Test
    @DisplayName("должен возвращать список из 5 ожидаемых вопросов")
    void checkCountOfQuestions() {

        List<Question> questions = questionService.findAll();
        Assertions.assertEquals(5, questions.size());

        List<String> actualQuestions = questions.stream().map(Question::getQuestion).collect(Collectors.toList());
        actualQuestions.containsAll(List.of("question1", "question2", "question3", "question4", "question5"));

    }

    @Test
    @DisplayName("должен возвращать список из 4 вариантов ответов на каждый вопрос")
    void checkCountOfAnswers() {
        List<Question> questions = questionService.findAll();

        for (Question question : questions) {
            Assertions.assertEquals(4, question.getAnswers().size());
        }
    }
}