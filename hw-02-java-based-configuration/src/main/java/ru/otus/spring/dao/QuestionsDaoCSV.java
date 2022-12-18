package ru.otus.spring.dao;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.*;

public class QuestionsDaoCSV implements QuestionsDao {

    private final String csvFilePath;

    public QuestionsDaoCSV(String csvFilePath) {
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
