package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.OutService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService implements ServiceCRUD<Genre> {

    private final GenreRepository genreRepository;
    private final OutService outService;

    @Override
    public void add(Genre element) {
        genreRepository.insert(element);
    }

    @Override
    public Genre findById(Long id) {
        return genreRepository.getById(id);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.getAll();
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }

    public void print(List<Genre> genres) {
        for (Genre genre : genres) {
            outService.outputStringNextLine(
                    "id = " + genre.getGenreId() +
                    "\t | title = " + genre.getTitle());
        }
    }
}
