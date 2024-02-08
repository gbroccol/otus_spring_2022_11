package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.repository.interf.CRUD;
import ru.otus.spring.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class AuthorRepository implements CRUD<Author> {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void insert (Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("first_name", author.getFirstName());
        params.addValue("last_name", author.getLastName());

        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(
                "insert into author (first_name, last_name) values (:first_name, :last_name)",
                params,
                kh,
                new String[]{"author_id"} );

        author.setAuthorId(Objects.requireNonNull(kh.getKey()).longValue());
    }

    @Override
    public Author getById(long authorId) {
        return namedParameterJdbcOperations.queryForObject(
                "select author_id, first_name, last_name from author where author_id = :author_id",
                Collections.singletonMap("author_id", authorId),
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
