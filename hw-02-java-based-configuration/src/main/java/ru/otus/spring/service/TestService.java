package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
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

    public TestService(@Value("${min.right.answers}") Integer minRightAnswers,
                       QuestionService questionService,
                       @Qualifier("ioServiceConsole") IOService ioService) {
        this.minRightAnswers = minRightAnswers;
        this.questionService = questionService;
        this.ioService = ioService;
    }

    public Boolean runTestReturnSuccess(@NonNull User user) {

        List<Question> questions = questionService.findAll();
        printTestRules(questions.size());

        try {
            int rightAnswersCount = 0;
            for (Question question : questions) {
                printQuestion(question);
                int answerNumber = getAnswer();
                rightAnswersCount += checkAnswerCorrect(question, answerNumber) ? 1 : 0;
            }

            boolean success = isSuccess(rightAnswersCount);
            printTestResult(user, success);
            return success;
        } catch (AnswerOutOfBoundException e) {
            ioService.outputString(Color.ANSI_RED + "Given number of answer is out of range. Please start test again" + Color.ANSI_RESET);
        } catch (NumberFormatException e) {
            ioService.outputString(Color.ANSI_RED + "Given answer is not a number. Please start test again" + Color.ANSI_RESET);
        }
        return false;
    }

    private void printTestRules(int questionSize) {
        ioService.outputString(Color.ANSI_BLUE + "There are " + questionSize + " question(-s). You must answer at least " +
                minRightAnswers + " question(-s) to receive credit\n" + Color.ANSI_RESET);
    }

    private void printQuestion(Question question) {

        ioService.outputString(question.getQuestion());

        int answerNumber = 0;
        for (Answer answer : question.getAnswers()) {
            ioService.outputString(answerNumber++ + ". " + answer.getAnswer());
        }
    }

    private int getAnswer() {
        System.out.print("Enter the number of right option: ");
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
        if (rightAnswersCount >= minRightAnswers) {
            return true;
        }
        return false;
    }

    private void printTestResult(User user, boolean success) {
        if (success) {
            ioService.outputString(user.getFirstName() + " " + user.getLastName() + ", congratulations! Test passed!");
        } else {
            ioService.outputString(user.getFirstName() + " " + user.getLastName() + ", you have not passed this test :(");
        }
    }
}
