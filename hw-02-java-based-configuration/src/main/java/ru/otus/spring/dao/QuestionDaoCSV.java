package ru.otus.spring.dao;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class QuestionDaoCSV implements QuestionDao {

    private String csvFilePath;

    public QuestionDaoCSV(@Value("${csv.file.path}") String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    @Override
    public List<String[]> getDataAsList() {
        BufferedInputStream bis = new BufferedInputStream(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(csvFilePath)));
        Reader br = new BufferedReader(new InputStreamReader(bis));
        try {
            return new CSVReaderBuilder(br).withSkipLines(1).build().readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
