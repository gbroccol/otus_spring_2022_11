package ru.otus.spring.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class Answer {

    String answer;
    Boolean correct;

}
