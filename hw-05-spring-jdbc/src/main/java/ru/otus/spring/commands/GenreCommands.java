package ru.otus.spring.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.impl.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {

    private final GenreService genreService;

    @ShellMethod(value = "Add new genre", key = {"ag", "add-genre"})
    public void addGenre(@ShellOption String genreName) {
        genreService.add(new Genre(null, genreName)); // todo где правильно делать создание объекта?
    }

    @ShellMethod(value = "Find genre by id", key = {"fg", "find-genre"})
    public void findById(@ShellOption Long id) {
        Genre genre = genreService.findById(id);
        genreService.print(List.of(genre));
    }

    @ShellMethod(value = "Find all genres", key = {"fgs", "find-all-genres"})
    public void findAll() {
        genreService.print(genreService.findAll());
    }

    @ShellMethod(value = "Delete genre by id", key = {"dg", "delete-genre"})
    public void deleteById(@ShellOption Long id) {
        genreService.deleteById(id);
    }
}
