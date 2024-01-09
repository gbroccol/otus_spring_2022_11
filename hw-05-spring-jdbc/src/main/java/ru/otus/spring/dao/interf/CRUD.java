package ru.otus.spring.dao.interf;

import java.util.List;

public interface CRUD<E> {

    void insert(E person);

    E getById(long id);

    List<E> getAll();

    void deleteById(long id);
}
