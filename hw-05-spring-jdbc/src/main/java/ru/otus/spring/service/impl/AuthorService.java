package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDaoJdbc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.service.IOService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService implements ServiceCRUD<Author>  {

    private final AuthorDaoJdbc authorDaoJdbc;
    private final IOService ioService;

    @Override
    public void add(Author element) {
        authorDaoJdbc.insert(element);
    }

    @Override
    public Author findById(Long id) {
        return authorDaoJdbc.getById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorDaoJdbc.getAll();
    }

    @Override
    public void deleteById(Long id) {
        authorDaoJdbc.deleteById(id);
    }

    public void print(List<Author> authors) {
        for (Author author : authors) {
            ioService.outputStringNextLine(
                    "id = " + author.getAuthorId() +
                            " | firstName = " + author.getFirstName() +
                            "\t | lastName = " + author.getLastName());
        }
    }
}
