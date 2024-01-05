package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.QuestionnairePropRules;
import ru.otus.spring.exception.AnswerOutOfBoundException;
import ru.otus.spring.exception.QuestionsReadingException;
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

    public TestService(QuestionnairePropRules questionnairePropRules,
                       QuestionService questionService,
                       @Qualifier("ioServiceConsole") IOService ioService,
                       MessageService messageService) {
        this.minRightAnswers = questionnairePropRules.getMinRightAnswers();
        this.questionService = questionService;
        this.ioService = ioService;
        this.messageService = messageService;
    }

    public void runTest(@NonNull User user) {

        try {
            List<Question> questions = questionService.findAll();
            printTestRules(questions.size());

            int rightAnswersCount = 0;
            for (Question question : questions) {
                printQuestion(question);
                int answerNumber = getAnswer();
                rightAnswersCount += checkAnswerCorrect(question, answerNumber) ? 1 : 0;
            }

            printTestResult(isSuccess(rightAnswersCount), rightAnswersCount);

        } catch (AnswerOutOfBoundException e) {
            ioService.outputStringNextLine(Color.ANSI_RED + "Given number of answer is out of range. Please start test again" + Color.ANSI_RESET);
        } catch (NumberFormatException e) {
            ioService.outputStringNextLine(Color.ANSI_RED + "Given answer is not a number. Please start test again" + Color.ANSI_RESET);
        } catch (QuestionsReadingException e) {
            ioService.outputStringNextLine(Color.ANSI_RED + "An error occurred while receiving test questions" + Color.ANSI_RESET);
        }
    }

    private void printTestRules(int questionsAmount) {
        ioService.outputString(Color.ANSI_BLUE + messageService.getMessage("test.questions.amount", questionsAmount) + Color.ANSI_RESET);
        ioService.outputString(Color.ANSI_BLUE + messageService.getMessage("test.questions.min.correct.amount", minRightAnswers) + Color.ANSI_RESET);
        ioService.outputString(System.lineSeparator());
    }

    private void printQuestion(Question question) {

        ioService.outputString(question.getQuestion() + "\n");
        int answerNumber = 0;
        for (Answer answer : question.getAnswers()) {
            ioService.outputString(answerNumber++ + ". " + answer.getAnswer() + "\n");
        }
    }

    private int getAnswer() {
        ioService.outputString(messageService.getMessage("enter.answer.number"));
        return ioService.inputInt();
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
        ioService.outputStringNextLine(messageService.getMessage("test.questions.answered.amount", answeredAmount));
        if (success) {
            ioService.outputStringNextLine(messageService.getMessage("test.finish.success"));
        } else {
            ioService.outputStringNextLine(messageService.getMessage("test.finish.fail"));
        }
    }
}
