package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.model.Answer;
import ru.otus.spring.model.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepository {

    private final static Integer QUESTION_POSITION = 0;
    private final static Integer OPTION_FROM_POSITION = 1;
    private final static Integer OPTION_TO_POSITION = 4;
    private final static Integer ANSWER_POSITION = 5;

    private final QuestionDao questionDao;

    @Override
    public List<Question> findAll() {

        List<String[]> data = questionDao.getDataAsList();
        List<Question> questions = new ArrayList<>();

        for (String[] line : data) {
            int correctAnswerPosition = Integer.parseInt(line[ANSWER_POSITION]);
            List<Answer> answers = new ArrayList<>();
            for (int pos = OPTION_FROM_POSITION; pos <= OPTION_TO_POSITION; pos++) {
                answers.add(new Answer(line[pos], pos == correctAnswerPosition));
            }
            questions.add(new Question(line[QUESTION_POSITION], answers));
        }
        return questions;
    }
}
