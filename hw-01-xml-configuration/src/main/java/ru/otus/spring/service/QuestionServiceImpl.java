package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.model.Answer;
import ru.otus.spring.model.Question;

import java.io.IOException;
import java.util.Collection;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionsDao questionsDao;

    public QuestionServiceImpl(QuestionsDao questionsDao) {
        this.questionsDao = questionsDao;
    }

    @Override
    public void printAllQuestions() {

        try {
            Collection<Question> questions = questionsDao.findAll();

            for (Question question : questions) {
                System.out.println("\n" + question.getQuestion());
                int counter = 1;
                for (Answer answer : question.getAnswers()) {
                    System.out.println(counter + ". " + answer.getAnswer());
                    counter++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
