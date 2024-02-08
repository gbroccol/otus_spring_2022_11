package ru.otus.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
//@NoArgsConstructor
//@RequiredArgsConstructor
public class Author {
    private Long authorId;
    private String firstName;
    private String lastName;


    public Author(Long authorId, String firstName, String lastName) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
