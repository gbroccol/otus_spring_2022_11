package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDaoJdbc;
import ru.otus.spring.dao.BookDaoJdbc;
import ru.otus.spring.dao.GenreDaoJdbc;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.OutService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final AuthorDaoJdbc authorDaoJdbc;
    private final BookDaoJdbc bookDaoJdbc;
    private final GenreDaoJdbc genreDaoJdbc;
    private final OutService outService;

    public void add(String title, long authorId, long genreId) {
        bookDaoJdbc.insert(
                new Book(null,
                        title,
                        authorDaoJdbc.getById(authorId),
                        genreDaoJdbc.getById(genreId)));
    }

    public Book findById(Long id) {
        return bookDaoJdbc.getById(id);
    }

    public List<Book> findAll() {
        return bookDaoJdbc.getAll();
    }

    public void deleteById(Long id) {
        bookDaoJdbc.deleteById(id);
    }

    public void print(List<Book> books) {
        for (Book book : books) {
            outService.outputStringNextLine(
                    "id = " + book.getBookId() +
                            " | title = " + book.getTitle() +
                            " | author's first name = " + book.getAuthor().getFirstName() +
                            " | author's last name = " + book.getAuthor().getLastName() +
                            " | genre = " + book.getGenre().getTitle());
        }
    }
}
