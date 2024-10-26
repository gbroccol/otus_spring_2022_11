package ru.otus.spring.service.impl;

import ru.otus.spring.model.Genre;

import java.util.List;

public interface GenreService {
    void save(String title);
    Genre findById(long id);
    List<Genre> findAll();
    void deleteById(long id);
}
