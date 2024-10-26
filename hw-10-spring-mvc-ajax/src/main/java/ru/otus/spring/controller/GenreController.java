package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.dto.GenreDto;
import ru.otus.spring.service.impl.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping(value = "/api/v1/genre")
    public List<GenreDto> getAll() {
        return genreService.findAll().stream()
                .map(GenreDto::toDto)
                .collect(Collectors.toList());
    }
}
