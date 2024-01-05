package ru.otus.spring.dao;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.QuestionnairePropFile;
import ru.otus.spring.exception.QuestionsReadingException;

import java.io.*;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class QuestionDaoCSV implements QuestionDao {

    private static final Logger logger = LoggerFactory.getLogger(QuestionDaoCSV.class);

    private final QuestionnairePropFile questionnairePropFile;

    @Override
    public List<String[]> getDataAsList() throws QuestionsReadingException {

        logger.info("We receive questions... Path to file - {} ", questionnairePropFile.getCsvFileName());

        BufferedInputStream bis = new BufferedInputStream(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(questionnairePropFile.getCsvFileName())));
        Reader br = new BufferedReader(new InputStreamReader(bis));
        try {
            return new CSVReaderBuilder(br).withSkipLines(1).build().readAll();
        } catch (IOException | CsvException e) {
            throw new QuestionsReadingException(e.getMessage());
        }
    }
}
