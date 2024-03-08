package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.spring.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b " +
            "from Book b " +
            "left join fetch b.author " +
            "left join fetch b.genre")
    List<Book> findAll();
}