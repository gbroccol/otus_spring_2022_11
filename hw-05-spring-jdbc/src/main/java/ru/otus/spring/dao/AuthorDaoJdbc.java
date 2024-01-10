package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.interf.CRUD;
import ru.otus.spring.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements CRUD<Author> {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Author author) {
        namedParameterJdbcOperations.update(
                "insert into author (first_name, last_name) values (:first_name, :last_name)",
                Map.of("first_name", author.getFirstName(),
                        "last_name", author.getLastName()));
    }

    @Override
    public Author getById(long authorId) {
        Map<String, Object> params = Collections.singletonMap("author_id", authorId);
        return namedParameterJdbcOperations.queryForObject(
                "select author_id, first_name, last_name from author where author_id = :author_id",
                params,
                new AuthorMapper()
        );
    }

    @Override
    public List<Author> getAll() {
        JdbcOperations jdbc = namedParameterJdbcOperations.getJdbcOperations();
        return jdbc.query("select author_id, first_name, last_name from author",
                new AuthorMapper());
    }

    @Override
    public void deleteById(long authorId) {
        Map<String, Object> params = Collections.singletonMap("author_id", authorId);
        namedParameterJdbcOperations.update(
                "delete from author where author_id = :author_id", params);
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("author_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            return new Author(id, firstName, lastName);
        }
    }
}
