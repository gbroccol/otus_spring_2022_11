package ru.otus.spring.repository.interf;

import java.util.List;
import java.util.Optional;

public interface CRUD<E> {

    E save(E object);
    Optional<E> findById(long id);
    List<E> findAll();
    void deleteById(long id);
}
