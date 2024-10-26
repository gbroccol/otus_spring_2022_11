package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.exception.NotFoundException;
import ru.otus.spring.exception.AuthorNotFoundException;
import ru.otus.spring.exception.GenreNotFoundException;
import ru.otus.spring.model.Review;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.model.Book;
import ru.otus.spring.repository.ReviewRepository;
import ru.otus.spring.service.BookService;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Book save(String title, long authorId, long genreId) {
        return this.save(0, title, authorId, genreId);
    }

    @Override
    @Transactional
    public Book save(Book book) {
        return this.save(0,
                book.getTitle(),
                book.getAuthor().getAuthorId(),
                book.getGenre().getGenreId());
    }

    @Override
    @Transactional
    public Book save(long bookId, String title, long authorId, long genreId) throws NotFoundException {
        try {
            var author = authorRepository.findById(authorId)
                    .orElseThrow(() -> new AuthorNotFoundException(MessageFormat.format("Book is not created. No author_id = {0}.", authorId)));
            var genre = genreRepository.findById(genreId)
                    .orElseThrow(() -> new GenreNotFoundException(MessageFormat.format("Book is not created. No genre_id = {0}.", genreId)));
            List<Review> reviews = null;
            if (bookId > 0) {
                reviews = reviewRepository.findByBook(bookId);
            }
            var book = new Book(bookId, title, author, genre, reviews);
            return bookRepository.save(book);
        } finally {
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(long id) {
        return bookRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("Book does not exist (book_id) = {0}.", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
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

}
