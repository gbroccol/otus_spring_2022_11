package ru.otus.spring.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.exception.AuthorNotFoundException;
import ru.otus.spring.model.Book;

import java.util.List;

public interface BookService {
    Book save(String title, long authorId, long genreId);

    @Transactional
    Book save(Book book);

    Book save(long bookId, String title, long authorId, long genreId) throws AuthorNotFoundException;

    Book findById(long id);

    @Transactional(readOnly = true)
    List<Book> findByTitle(String title);

    List<Book> findAll();

    void deleteById(long id);
}
