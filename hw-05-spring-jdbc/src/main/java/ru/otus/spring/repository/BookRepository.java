package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Genre;
import ru.otus.spring.repository.ext.BookResultSetExtractor;
import ru.otus.spring.repository.interf.CRUD;
import ru.otus.spring.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class BookRepository implements CRUD<Book> {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void insert(Book book) {
        namedParameterJdbcOperations.update(
                "insert into book (title, author_id, genre_id) values (:title, :author_id, :genre_id)",
                Map.of("title", book.getTitle(),
                        "author_id", book.getAuthor().getAuthorId(),
                        "genre_id", book.getGenre().getGenreId()));
    }

    @Override
    public Book getById(long bookId) {
        Map<String, Object> params = Collections.singletonMap("book_id", bookId);
        return namedParameterJdbcOperations.queryForObject(
                "select " +
                        "b.book_id          as book_id, " +
                        "b.title            as book_title, " +
                        "a.author_id        as author_id, " +
                        "a.first_name       as author_first_name, " +
                        "a.last_name        as author_last_name, " +
                        "g.genre_id         as genre_id, " +
                        "g.title            as genre_title, " +
                        "from book b " +
                        "left join author a on a.author_id = b.author_id " +
                        "left join genre g on g.genre_id = b.genre_id " +
                        "where book_id = :book_id",
                params,
                new BookRepository.BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        Map<Long, Book> bookMap =
                namedParameterJdbcOperations.query(
                        "select " +
                                "b.book_id          as book_id, " +
                                "b.title            as book_title, " +
                                "a.author_id        as author_id, " +
                                "a.first_name       as author_first_name, " +
                                "a.last_name        as author_last_name, " +
                                "g.genre_id         as genre_id, " +
                                "g.title            as genre_title, " +
                                "from book b " +
                                "left join author a on a.author_id = b.author_id " +
                                "left join genre g on g.genre_id = b.genre_id ",
                        new BookResultSetExtractor());
        return new ArrayList<>(Objects.requireNonNull(bookMap).values());
    }

    @Override
    public void deleteById(long bookId) {
        Map<String, Object> params = Collections.singletonMap("book_id", bookId);
        namedParameterJdbcOperations.update(
                "delete from book where book_id = :book_id", params);
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long bookId = resultSet.getLong("book_id");
            String bookTitle = resultSet.getString("book_title");
            Author author = new Author(resultSet.getLong("author_id"),
                    resultSet.getString("author_first_name"),
                    resultSet.getString("author_last_name"));
            Genre genre = new Genre(resultSet.getLong("genre_id"),
                    resultSet.getString("genre_title"));
            return new Book(bookId, bookTitle, author, genre);
        }
    }
}
