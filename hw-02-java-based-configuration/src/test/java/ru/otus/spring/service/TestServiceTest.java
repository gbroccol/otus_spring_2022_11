package ru.otus.spring.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.model.Answer;
import ru.otus.spring.model.Question;
import ru.otus.spring.model.User;
import ru.otus.spring.service.impl.QuestionService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@DisplayName("Сервис тестирования ")
@ExtendWith(MockitoExtension.class)
class TestServiceTest {

    @Mock
    private QuestionService questionService;

    @Mock
    private IOService ioService;

    private TestService testService;

    @BeforeEach
    void setUp() {

        testService = new TestService(3, questionService, ioService);

        List<Answer> answerListMock = new ArrayList<>();
        answerListMock.add(new Answer("answer1", true));
        answerListMock.add(new Answer("answer2", false));
        answerListMock.add(new Answer("answer3", false));
        answerListMock.add(new Answer("answer4", false));

        List<Question> questionListMock = new ArrayList<>();
        questionListMock.add(new Question("question1", answerListMock));
        questionListMock.add(new Question("question2", answerListMock));
        questionListMock.add(new Question("question3", answerListMock));
        questionListMock.add(new Question("question4", answerListMock));
        questionListMock.add(new Question("question5", answerListMock));

        given(questionService.findAll()).willReturn(questionListMock);
    }

    @Test
    @DisplayName("выбрасывает исключение, если пользователь = null")
    void checkNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> testService.runTestReturnSuccess(null));
    }

// ----------- >>>>> Подскажите пожалуйста как тестировать исключения, если мы их отловили или в этом нет смысла? <<<<< -----------

//    @Test
//    @DisplayName("выбрасывает исключение, если не существует ответа для введенного значения")
//    void checkAnswerOutOfBoundException() {
//
//        List<Question> questionListMock = new ArrayList<>();
//        List<Answer> answerListMock = new ArrayList<>();
//        answerListMock.add(new Answer("answer1", true));
//        Question questionMock = new Question("question1", answerListMock);
//        questionListMock.add(questionMock);
//        given(questionService.findAll()).willReturn(questionListMock);
//        given(ioService.readInt()).willReturn(-1);
//
//        Assertions.assertThrows(AnswerOutOfBoundException.class, () -> testService.testUser(new User("Ivan", "Ivanov")));
//    }

    @Test
    @DisplayName("возвращает TRUE, если тест пройден")
    void returnTrueIfTestPassed() {
        given(ioService.readInt()).willReturn(0);
        Assertions.assertTrue(testService.runTestReturnSuccess(new User("Ivan", "Ivanov")));
    }

    @Test
    @DisplayName("возвращает FALSE, если тест не пройден")
    void returnFalseIfTestNotPassed() {
        given(ioService.readInt()).willReturn(1);
        Assertions.assertFalse(testService.runTestReturnSuccess(new User("Ivan", "Ivanov")));
    }
}