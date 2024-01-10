package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.GenreDaoJdbc;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.OutService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService implements ServiceCRUD<Genre> {

    private final GenreDaoJdbc genreDaoJdbc;
    private final OutService outService;

    @Override
    public void add(Genre element) {
        genreDaoJdbc.insert(element);
    }

    @Override
    public Genre findById(Long id) {
        return genreDaoJdbc.getById(id);
    }

    @Override
    public List<Genre> findAll() {
        return genreDaoJdbc.getAll();
    }

    @Override
    public void deleteById(Long id) {
        genreDaoJdbc.deleteById(id);
    }

    public void print(List<Genre> genres) {
        for (Genre genre : genres) {
            outService.outputStringNextLine(
                    "id = " + genre.getGenreId() +
                    " | title = " + genre.getTitle());
        }
    }
}
