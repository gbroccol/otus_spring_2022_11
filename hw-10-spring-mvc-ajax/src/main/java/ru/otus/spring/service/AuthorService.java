package ru.otus.spring.service;

import ru.otus.spring.model.Author;

import java.util.List;

public interface AuthorService {

    void save(String firstName, String lastName);

    Author findById(long id);

    List<Author> findAll();

    void deleteById(long id);
}
