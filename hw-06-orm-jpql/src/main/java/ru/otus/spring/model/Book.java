package ru.otus.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long bookId;
    private String title;
    private Author author;
    private Genre genre;


}
