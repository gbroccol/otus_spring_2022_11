package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.OutService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final OutService outService;

    @Override
    public void save(String title) {
        genreRepository.save(new Genre(0, title));
    }

    @Override
    public void update(long genreId, String title) {
        genreRepository.save(new Genre(genreId, title));
    }

    @Override
    public Genre findById(long id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public void print(List<Genre> genres) {
        for (Genre genre : genres) {
            outService.outputStringNextLine(
                    "id = " + genre.getGenreId() +
                            "\t | title = " + genre.getTitle());
        }
    }
}
