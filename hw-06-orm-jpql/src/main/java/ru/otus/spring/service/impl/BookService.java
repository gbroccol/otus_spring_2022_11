package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.model.Book;
import ru.otus.spring.service.OutService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final OutService outService;

    public void add(String title, long authorId, long genreId) {
        bookRepository.insert(
                new Book(null,
                        title,
                        authorRepository.getById(authorId),
                        genreRepository.getById(genreId)));
    }

    public Book findById(Long id) {
        return bookRepository.getById(id);
    }

    public List<Book> findAll() {
        return bookRepository.getAll();
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public void print(List<Book> books) {
        for (Book book : books) {
            outService.outputStringNextLine(
                    "id = " + book.getBookId() +
                            "\n\t | title = " + book.getTitle() +
                            "\n\t | author's first name = " + book.getAuthor().getFirstName() +
                            "\n\t | author's last name = " + book.getAuthor().getLastName() +
                            "\n\t | genre = " + book.getGenre().getTitle());
        }
    }
}
