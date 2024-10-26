package ru.otus.spring.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.model.Book;

@Data
@AllArgsConstructor
public class BookDto {

    private Long id;

    @NotNull
    private String title;
    private AuthorDto author;
    private GenreDto genre;

    public static BookDto toDto(Book book) {
        return new BookDto(book.getBookId(),
                book.getTitle(),
                AuthorDto.toDto(book.getAuthor()),
                GenreDto.toDto(book.getGenre()));
    }

    public Book toDomainObject() {
        return new Book(id,
                title,
                author.toDomainObject(),
                genre.toDomainObject());
    }
}
