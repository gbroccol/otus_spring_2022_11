package ru.otus.spring.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.model.Author;
import ru.otus.spring.service.impl.AuthorService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {

    private final AuthorService authorService;

    @ShellMethod(value = "Add new author", key = {"aa", "add-author"})
    public void addAuthor(@ShellOption String firstName,
                          @ShellOption String lastName) {
        authorService.save(firstName, lastName);
    }

    @ShellMethod(value = "Find author by id", key = {"fa", "find-author"})
    public void findById(@ShellOption Long id) {
        Author author = authorService.findById(id);
        authorService.print(List.of(author));
    }

    @ShellMethod(value = "Find all authors", key = {"fas", "find-all-authors"})
    public void findAll() {
        List<Author> authors = authorService.findAll();
        authorService.print(authors);
    }

    @ShellMethod(value = "Delete author by id", key = {"da", "delete-author"})
    public void deleteById(@ShellOption Long id) {
        authorService.deleteById(id);
    }
}
