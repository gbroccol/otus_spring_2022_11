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
//    @Size(min = 3, max = 20)
    private String title;
//    private AuthorDto author;
//    private GenreDto genre;
//    private List<ReviewDto> reviews;

    public static BookDto toDto(Book book) {
        return new BookDto(book.getBookId(),
                book.getTitle());
    }

    public Book toDomainObject() {
        return new Book(id, title, null, null, null); // fix
    }
}
