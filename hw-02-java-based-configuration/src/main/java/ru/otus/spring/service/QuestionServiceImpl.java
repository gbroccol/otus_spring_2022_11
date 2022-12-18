package ru.otus.spring.service;

import ru.otus.spring.model.Answer;
import ru.otus.spring.model.Question;
import ru.otus.spring.repository.QuestionsRepository;

import java.util.Collection;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionsRepository questionsRepository;

    public QuestionServiceImpl(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    @Override
    public void printAllQuestions() {

        Collection<Question> questions = questionsRepository.findAll();

        for (Question question : questions) {
            System.out.println("\n" + question.getQuestion());
            int counter = 1;
            for (Answer answer : question.getAnswers()) {
                System.out.println(counter + ". " + answer.getAnswer());
                counter++;
            }
        }

    }
}
