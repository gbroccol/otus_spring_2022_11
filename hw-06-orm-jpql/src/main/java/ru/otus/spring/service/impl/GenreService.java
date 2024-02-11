package ru.otus.spring.service.impl;

import ru.otus.spring.model.Genre;

import java.util.List;

public interface GenreService {
    void save(String title);
    void update(long genreId, String title);
    Genre findById(long id);
    List<Genre> findAll();
    void deleteById(long id);
    void print(List<Genre> genre);
}
