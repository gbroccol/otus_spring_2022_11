package ru.otus.spring.service.impl;

import ru.otus.spring.model.Book;

import java.util.List;

public interface BookService {
   void save(String title, long authorId, long genreId);
   void save(long bookId, String title, long authorId, long genreId);
   Book findById(long id);
   List<Book> findAll();
   void deleteById(long id);
   void print(List<Book> books);
}
