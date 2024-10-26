package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import ru.otus.spring.model.Author;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Dao для работы с авторами должно")
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
class AuthorRepositoryTest {

    private static final Long EXISTING_AUTHOR_ID = 4L;
    private static final String EXISTING_AUTHOR_FIRST_NAME = "Лев";
    private static final String EXISTING_AUTHOR_LAST_NAME = "Толстой";

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager tem;

    @BeforeTransaction
    void beforeTransaction() {
        System.out.println("beforeTransaction");
    }

    @AfterTransaction
    void afterTransaction() {
        System.out.println("afterTransaction");
    }

    @DisplayName("возвращать всех авторов по имени")
    @Test
    void shouldReturnExpectedAuthorsByFirstName() {
        String expectedFirstName = "Михаил";
        List<Author> authors = authorRepository.findAllByFirstName(expectedFirstName);
        assertThat(authors)
                .isNotNull()
                .hasSize(2)
                .allMatch(a -> Objects.equals(a.getFirstName(), expectedFirstName));
    }

    @DisplayName("обновить автора по id")
    @Test
    void shouldUpdateAuthorById() {

        String expectedLastName = "new_last_name_for_test";

        authorRepository.updateAuthorById(EXISTING_AUTHOR_ID, expectedLastName);
        Author author = authorRepository.findById(EXISTING_AUTHOR_ID).orElse(null);
        assertThat(author)
                .isNotNull()
                .matches(a -> Objects.equals(a.getLastName(), expectedLastName));
    }

    @DisplayName("удалить автора по id (JPA метод)")
    @Test
    void shouldDeleteAuthorByIdUsingJpaMethod() {
        authorRepository.deleteById(EXISTING_AUTHOR_ID);
        Author author = authorRepository.findById(EXISTING_AUTHOR_ID).orElse(null);
        assertThat(author).isNull();
    }

    @DisplayName("удалить автора по id (JPQL)")
    @Test
    void shouldDeleteAuthorByIdUsingJpql() {
        int deletedAmount = authorRepository.deleteByIdUsingJPQL(EXISTING_AUTHOR_ID);
        assertThat(deletedAmount).isEqualTo(1);
        Author author = authorRepository.findById(EXISTING_AUTHOR_ID).orElse(null);
        assertThat(author).isNull();
    }
}
