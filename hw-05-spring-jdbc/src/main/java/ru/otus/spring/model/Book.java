package ru.otus.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
//@NoArgsConstructor
//@RequiredArgsConstructor
public class Book {
    private Long bookId;
    private String title;
    private Author author;
    private Genre genre;

    public Book(Long bookId, String title, Author author, Genre genre) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}
