package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.QuestionnaireProperties;
import ru.otus.spring.exception.AnswerOutOfBoundException;
import ru.otus.spring.model.Answer;
import ru.otus.spring.model.Question;
import ru.otus.spring.model.User;
import ru.otus.spring.service.impl.QuestionService;
import ru.otus.spring.util.Color;

import java.util.List;

@Service
public class TestService {

    private final Integer minRightAnswers;
    private final QuestionService questionService;
    private final IOService ioService;
    private final MessageService messageService;

    public TestService(QuestionnaireProperties questionnaireProperties,
                       QuestionService questionService,
                       @Qualifier("ioServiceConsole") IOService ioService,
                       MessageService messageService) {
        this.minRightAnswers = questionnaireProperties.getMinRightAnswers();
        this.questionService = questionService;
        this.ioService = ioService;
        this.messageService = messageService;
    }

    public void runTest(@NonNull User user) {

        List<Question> questions = questionService.findAll();
        printTestRules(questions.size());

        try {
            int rightAnswersCount = 0;
            for (Question question : questions) {
                printQuestion(question);
                int answerNumber = getAnswer();
                rightAnswersCount += checkAnswerCorrect(question, answerNumber) ? 1 : 0;
            }
            printTestResult(isSuccess(rightAnswersCount), rightAnswersCount);
        } catch (AnswerOutOfBoundException e) {
            ioService.outputString(Color.ANSI_RED + "Given number of answer is out of range. Please start test again" + Color.ANSI_RESET);
        } catch (NumberFormatException e) {
            ioService.outputString(Color.ANSI_RED + "Given answer is not a number. Please start test again" + Color.ANSI_RESET);
        }
    }

    private void printTestRules(int questionSize) {
        ioService.outputString(Color.ANSI_BLUE + messageService.getMessageTestQuestionsAmount(new Integer[]{questionSize}) + Color.ANSI_RESET);
        ioService.outputString(Color.ANSI_BLUE + messageService.getMessageTestQuestionsMinCorrectAmount(new Integer[]{minRightAnswers}) + Color.ANSI_RESET);
        ioService.outputString("\n");
    }

    private void printQuestion(Question question) {

        ioService.outputString(question.getQuestion() + "\n");
        int answerNumber = 0;
        for (Answer answer : question.getAnswers()) {
            ioService.outputString(answerNumber++ + ". " + answer.getAnswer() + "\n");
        }
    }

    private int getAnswer() {
        ioService.outputString(messageService.getMessageEnterAnswerNumber());
        return ioService.readInt();
    }

    private boolean checkAnswerCorrect(Question question, int answerNumber) throws AnswerOutOfBoundException {

        if (answerNumber < 0 || answerNumber > question.getAnswers().size() - 1)
            throw new AnswerOutOfBoundException("Given number of answer is out of range");

        if (question.getAnswers().get(answerNumber).getCorrect()) {
            ioService.outputString(Color.ANSI_GREEN + "OK\n" + Color.ANSI_RESET);
            return true;
        }
        ioService.outputString(Color.ANSI_RED + "ERROR\n" + Color.ANSI_RESET);
        return false;
    }

    private Boolean isSuccess(int rightAnswersCount) {
        return rightAnswersCount >= minRightAnswers;
    }

    private void printTestResult(boolean success, int answeredAmount) {
        ioService.outputString(messageService.getMessageTestQuestionsAnsweredAmount(new Integer[]{answeredAmount}));
        if (success) {
            ioService.outputString(messageService.getMessageTestFinishSuccess());
        } else {
            ioService.outputString(messageService.getMessageTestFinishFail());
        }
    }
}
