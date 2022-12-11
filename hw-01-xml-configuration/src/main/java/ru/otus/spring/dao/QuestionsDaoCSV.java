package ru.otus.spring.dao;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import ru.otus.spring.model.Answer;
import ru.otus.spring.model.Question;

import java.io.*;
import java.util.*;

public class QuestionsDaoCSV implements QuestionsDao {

    private final static Integer QUESTION_POSITION = 0;
    private final static Integer ANSWER_POSITION = 1;
    private final static Integer IS_CORRECT_POSITION = 2;

    private final String csvFilePath;

    public QuestionsDaoCSV(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    private List<String[]> readCsv() {
        BufferedInputStream bis = new BufferedInputStream(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(csvFilePath)));
        Reader br = new BufferedReader(new InputStreamReader(bis));
        try {
            return new CSVReaderBuilder(br).withSkipLines(1).build().readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Question> findAll() {

        List<String[]> csvText = readCsv();
        Map <String, Question> questions = new HashMap<>();

        for (String[] line : csvText) {
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

        return questions.values();
    }
}
