package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void save(String title) {
        genreRepository.save(new Genre(null, title));
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findById(long id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional
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
