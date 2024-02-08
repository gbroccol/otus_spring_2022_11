package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.model.Author;
import ru.otus.spring.service.OutService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService implements ServiceCRUD<Author>  {

    private final AuthorRepository authorRepository;
    private final OutService outService;

    @Override
    public void add(Author element) {
        authorRepository.insert(element);
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.getById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.getAll();
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    public void print(List<Author> authors) {
        for (Author author : authors) {
            outService.outputStringNextLine(
                    "id = " + author.getAuthorId() +
                            "\t | firstName = " + author.getFirstName() +
                            "\t | lastName = " + author.getLastName());
        }
    }
}
