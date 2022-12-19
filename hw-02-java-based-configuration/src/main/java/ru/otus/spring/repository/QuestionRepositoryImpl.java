package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.model.Answer;
import ru.otus.spring.model.Question;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepository {

    private final static Integer QUESTION_POSITION = 0;
    private final static Integer ANSWER_POSITION = 1;
    private final static Integer IS_CORRECT_POSITION = 2;

    private final QuestionDao questionDao;

    @Override
    public List<Question> findAll() {

        List<String[]> data = questionDao.getDataAsList();
        Map<String, Question> questions = new HashMap<>();

        for (String[] line : data) {
            Question question = questions.get(line[QUESTION_POSITION]);
            if (question == null) {
                List <Answer> answers = new ArrayList<>();
                boolean isCorrect = line[IS_CORRECT_POSITION].equalsIgnoreCase("true");
                answers.add(new Answer(line[ANSWER_POSITION], isCorrect));
                question = new Question(line[QUESTION_POSITION], answers);
                questions.put(line[QUESTION_POSITION], question);
            } else {
                boolean isCorrect = line[IS_CORRECT_POSITION].equalsIgnoreCase("true");
                question.getAnswers().add(new Answer(line[ANSWER_POSITION], isCorrect));
            }
        }

        return new ArrayList<>(questions.values());
    }
}
