package ru.otus.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.model.Genre;

@Data
@AllArgsConstructor
public class GenreDto {

    private Long id;
    private String title;

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getGenreId(),
                genre.getTitle());
    }

    public Genre toDomainObject() {
        return new Genre(id, title);
    }
}
