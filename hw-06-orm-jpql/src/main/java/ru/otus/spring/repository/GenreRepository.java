package ru.otus.spring.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Genre;
import ru.otus.spring.repository.interf.CRUD;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class GenreRepository implements CRUD<Genre> {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Genre save(Genre genre) {
        if (genre.getGenreId() <= 0) {
            em.persist(genre);
            return genre;
        }
        return em.merge(genre);
    }

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> findAll() {
        return em.createQuery("select g from Genre g", Genre.class)
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
                        "from Genre g " +
                        "where g.genre_id = :genre_id");
        query.setParameter("genre_id", id);
        query.executeUpdate();
    }
}