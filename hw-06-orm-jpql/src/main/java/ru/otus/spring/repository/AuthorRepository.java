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

//        EntityGraph<?> entityGraph = em.getEntityGraph("otus-student-avatars-entity-graph");
//        TypedQuery<Author> query = em.createQuery("select s from Author s join fetch s.emails", Author.class);
//        query.setHint("javax.persistence.fetchgraph", entityGraph);
//        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery(
                "delete " +
                        "from Author a " +
                        "where a.author_id = :author_id");
        query.setParameter("author_id", id);
        query.executeUpdate();
    }
}
