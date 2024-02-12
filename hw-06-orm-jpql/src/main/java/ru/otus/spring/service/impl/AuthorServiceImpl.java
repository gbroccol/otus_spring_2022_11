package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.model.Author;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.service.OutService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final OutService outService;

    @Override
    @Transactional
    public void save(String firstName, String lastName) {
        authorRepository.save(new Author(0, firstName, lastName));
    }

    @Override
    @Transactional(readOnly = true)
    public Author findById(long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public void print(List<Author> authors) {
        for (Author author : authors) {
            outService.outputStringNextLine(
                    "id = " + author.getAuthorId() +
                            "\t | firstName = " + author.getFirstName() +
                            "\t | lastName = " + author.getLastName());
        }
    }
}
