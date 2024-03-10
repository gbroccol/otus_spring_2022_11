package ru.otus.spring.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.model.Book;

import java.util.List;

public interface BookService {
   void save(String title, long authorId, long genreId);

    @Transactional
    void save(Book book);

    void save(long bookId, String title, long authorId, long genreId);
   Book findById(long id);

    @Transactional(readOnly = true)
    List<Book> findByTitle(String title);

    List<Book> findAll();
   void deleteById(long id);
   void print(List<Book> books);
}
