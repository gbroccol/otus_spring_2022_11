package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.interf.CRUD;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements CRUD<Genre> {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Genre genre) {
        namedParameterJdbcOperations.update(
                "insert into genre (genre_id, title) values (:genre_id, :title)",
                Map.of("genre_id", genre.getGenreId(),
                        "title", genre.getTitle()));
    }

    @Override
    public Genre getById(long genreId) {
        Map<String, Object> params = Collections.singletonMap("genre_id", genreId);
        return namedParameterJdbcOperations.queryForObject(
                "select genre_id, title from genre where genre_id = :genre_id",
                params,
                new GenreDaoJdbc.GenreMapper()
        );
    }

    @Override
    public List<Genre> getAll() {
        JdbcOperations jdbc = namedParameterJdbcOperations.getJdbcOperations();
        return jdbc.query("select genre_id, title from genre",
                new GenreDaoJdbc.GenreMapper());
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
