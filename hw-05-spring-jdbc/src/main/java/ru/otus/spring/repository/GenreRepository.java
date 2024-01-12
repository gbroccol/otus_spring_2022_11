package ru.otus.spring.repository;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.repository.interf.CRUD;
import ru.otus.spring.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GenreRepository implements CRUD<Genre> {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreRepository(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Genre genre) {
        namedParameterJdbcOperations.update(
                "insert into genre (title) values (:title)",
                Map.of("title", genre.getTitle()));
    }

    @Override
    public Genre getById(long genreId) {
        Map<String, Object> params = Collections.singletonMap("genre_id", genreId);
        return namedParameterJdbcOperations.queryForObject(
                "select genre_id, title from genre where genre_id = :genre_id",
                params,
                new GenreRepository.GenreMapper()
        );
    }

    @Override
    public List<Genre> getAll() {
        JdbcOperations jdbc = namedParameterJdbcOperations.getJdbcOperations();
        return jdbc.query("select genre_id, title from genre",
                new GenreRepository.GenreMapper());
    }

    @Override
    public void deleteById(long genreId) {
        Map<String, Object> params = Collections.singletonMap("genre_id", genreId);
        namedParameterJdbcOperations.update(
                "delete from genre where genre_id = :genre_id", params);
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long genreId = resultSet.getLong("genre_id");
            String title = resultSet.getString("title");
            return new Genre(genreId, title);
        }
    }
}
