package ru.otus.spring.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Author;
import ru.otus.spring.repository.interf.CRUD;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AuthorRepository implements CRUD<Author> {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Author save(Author author) {
        if (author.getAuthorId() <= 0) {
            em.persist(author);
            return author;
        }
        return em.merge(author);
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> findAll() {
        return em.createQuery("select a from Author a", Author.class)
                .getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery(
                "delete " +
                        "from Author a " +
                        "where a.authorId = :authorId");
        query.setParameter("authorId", id);
        query.executeUpdate();
    }
}
