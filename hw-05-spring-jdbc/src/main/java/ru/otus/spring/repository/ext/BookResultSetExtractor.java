package ru.otus.spring.repository.ext;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BookResultSetExtractor implements ResultSetExtractor<Map<Long, Book>> {
    @Override
    public Map<Long, Book> extractData(ResultSet rs) throws SQLException, DataAccessException {

        Map<Long, Book> books = new HashMap<>();
        while (rs.next()) {

            Author author = new Author(rs.getLong("author_id"),
                    rs.getString("author_first_name"),
                    rs.getString("author_last_name"));

            Genre genre = new Genre(rs.getLong("genre_id"),
                    rs.getString("genre_title"));

            Book book = new Book(rs.getLong("book_id"),
                    rs.getString("book_title"),
                    author,
                    genre);
            books.put(book.getBookId(), book);
        }
        return books;
    }
}
