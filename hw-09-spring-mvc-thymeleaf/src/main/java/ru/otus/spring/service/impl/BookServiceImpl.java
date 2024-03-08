package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.exception.AuthorNotExistsException;
import ru.otus.spring.exception.GenreNotExistsException;
import ru.otus.spring.model.Review;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.model.Book;
import ru.otus.spring.repository.ReviewRepository;
import ru.otus.spring.service.OutService;
import ru.otus.spring.util.Color;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final ReviewRepository reviewRepository;
    private final OutService outService;

    @Override
    @Transactional
    public void save(String title, long authorId, long genreId) {
        this.save(0, title, authorId, genreId);
    }

    @Override
    @Transactional
    public void save(long bookId, String title, long authorId, long genreId) {
        try {
            var author = authorRepository.findById(authorId)
                    .orElseThrow(() -> new AuthorNotExistsException(MessageFormat.format("Book is not created. No author_id = {0}.", authorId)));
            var genre = genreRepository.findById(genreId)
                    .orElseThrow(() -> new GenreNotExistsException(MessageFormat.format("Book is not created. No genre_id = {0}.", genreId)));
            List<Review> reviews = null;
            if (bookId > 0) {
                reviews = reviewRepository.findByBook(bookId);
            }
            var book = new Book(bookId, title, author, genre, reviews);
            bookRepository.save(book);
        } catch (AuthorNotExistsException | GenreNotExistsException e) {
            outService.outputStringNextLine(Color.ANSI_RED + e.getMessage() + Color.ANSI_RESET);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            book.getAuthor().getLastName();
            book.getGenre().getTitle();
        }
        return books;
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
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
