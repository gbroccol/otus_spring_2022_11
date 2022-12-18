package ru.otus.spring.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class Question {

    String question;
    List<Answer> answers;

}
