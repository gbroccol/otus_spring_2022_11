package ru.otus.spring.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.repository.interf.CRUD;
import ru.otus.spring.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Repository
@AllArgsConstructor
public class BookRepository implements CRUD<Book> {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Book save(Book book) {
        if (book.getBookId() <= 0) {
            em.persist(book);
            return book;
        }
        return em.merge(book);
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();

//        EntityGraph<?> entityGraph = em.getEntityGraph("otus-student-avatars-entity-graph");
//        TypedQuery<Author> query = em.createQuery("select s from Author s join fetch s.emails", Author.class);
//        query.setHint("javax.persistence.fetchgraph", entityGraph);
//        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery(
                "delete " +
                        "from Book b " +
                        "where b.book_id = :book_id");
        query.setParameter("book_id", id);
        query.executeUpdate();
    }
}