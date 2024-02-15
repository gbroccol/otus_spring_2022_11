package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.model.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByFirstName(String firstName);

    @Modifying
    @Query("update Author a " +
            "set a.lastName = :lastName " +
            "where a.authorId = :authorId")
    void updateAuthorById(@Param("authorId") Long authorId,
                         @Param("lastName") String lastName);

    @Modifying
    @Query("delete Author a " +
            "where a.authorId = :authorId")
    int deleteByIdUsingJPQL(@Param("authorId") Long authorId);

}
