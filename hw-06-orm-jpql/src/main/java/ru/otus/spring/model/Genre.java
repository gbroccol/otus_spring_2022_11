package ru.otus.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
//@NoArgsConstructor
//@RequiredArgsConstructor
public class Genre {
    private Long genreId;
    private String title;

    public Genre(Long genreId, String title) {
        this.genreId = genreId;
        this.title = title;
    }
}
