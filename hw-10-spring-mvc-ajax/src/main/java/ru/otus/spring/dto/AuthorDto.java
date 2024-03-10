package ru.otus.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.model.Author;

@Data
@AllArgsConstructor
public class AuthorDto {

    private Long id;
    private String firstName;
    private String lastName;

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getAuthorId(),
                author.getFirstName(),
                author.getLastName());
    }
}
