package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import ru.otus.spring.model.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import(AuthorRepository.class)
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
class AuthorRepositoryTest {

    private static final int EXPECTED_AUTHORS_COUNT = 10;
    private static final Long EXISTING_AUTHOR_ID = 5L;
    private static final String EXISTING_AUTHOR_FIRST_NAME = "Александр";
    private static final String EXISTING_AUTHOR_LAST_NAME = "Пушкин";

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeTransaction
    void beforeTransaction(){
        System.out.println("beforeTransaction");
    }

    @AfterTransaction
    void afterTransaction(){
        System.out.println("afterTransaction");
    }

    //@Rollback(value = false)
    //@Commit
    @DisplayName("добавлять автора в БД")
    @Test
    void shouldInsertAuthor() {
        Author expectedAuthor = new Author(null, "Ivan", "Ivanov");
        authorRepository.insert(expectedAuthor);
        Author actualAuthor = authorRepository.getById(expectedAuthor.getAuthorId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемого автора по его id")
    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME);
        Author actualAuthor = authorRepository.getById(expectedAuthor.getAuthorId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("удалять заданного автора по его id")
    @Test
    void shouldCorrectDeleteAuthorById() {
        assertThatCode(() -> authorRepository.getById(EXISTING_AUTHOR_ID))
                .doesNotThrowAnyException();

        authorRepository.deleteById(EXISTING_AUTHOR_ID);

        assertThatThrownBy(() -> authorRepository.getById(EXISTING_AUTHOR_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void shouldReturnExpectedAuthorsList() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME);
        List<Author> actualAuthorList = authorRepository.getAll();
        assertThat(actualAuthorList.size()).isEqualTo(EXPECTED_AUTHORS_COUNT);
        assertThat(actualAuthorList)
                .contains(expectedAuthor);
    }
}