package ru.otus.spring.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@DisplayName("Dao для работы с книгами должно")
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
class BookRepositoryTest {

    private static final int EXPECTED_BOOKS_COUNT = 3;
    private static final Long EXISTING_BOOK_ID = 1L;
    private static final String EXISTING_BOOK_TITLE = "Мастер и Маргарита";
    private static final Author EXISTING_BOOK_AUTHOR = new Author(1L, "Михаил", "Булгаков");
    private static final Genre EXISTING_BOOK_GENRE = new Genre(4L, "фантастика");

    private static final int EXPECTED_QUERIES_COUNT = 2;

    @Autowired
    private BookRepository bookRepository;

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

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book expectedBook = new Book(null, "Собачье сердце", EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE, new ArrayList<>());
        expectedBook.setBookId(bookRepository.save(expectedBook).getBookId());
        Book actualBook = bookRepository.findById(expectedBook.getBookId()).orElse(null);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по её id")
    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook =
                new Book(EXISTING_BOOK_ID,
                        EXISTING_BOOK_TITLE,
                        EXISTING_BOOK_AUTHOR,
                        EXISTING_BOOK_GENRE,
                        new ArrayList<>());
        Book actualBook = bookRepository.findById(expectedBook.getBookId()).orElse(null);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалять заданную книгу по его id")
    @Test
    void shouldCorrectDeleteBookById() {
        assertThatCode(() -> bookRepository.findById(EXISTING_BOOK_ID)).doesNotThrowAnyException();
        bookRepository.deleteById(EXISTING_BOOK_ID);
        assertThat(bookRepository.findById(EXISTING_BOOK_ID)).isEmpty();
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {
        SessionFactory sessionFactory = tem.getEntityManager().getEntityManagerFactory().unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        System.out.println("\n\n\n\n-START---------------------------------------------------------------------------------------------------------");
        List<Book> actualBookList = bookRepository.findAll();
        assertThat(actualBookList)
                .isNotNull()
                .hasSize(EXPECTED_BOOKS_COUNT)
                .allMatch(s -> !s.getTitle().isEmpty())
                .allMatch(s -> s.getAuthor().getLastName() != null)
                .allMatch(s -> s.getGenre().getTitle() != null)
                .allMatch(s -> s.getReviews() != null && s.getReviews().isEmpty()); // добавить отзывы?
        System.out.println("-FINISH---------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }
}
