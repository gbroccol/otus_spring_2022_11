package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping(value = "/api/v1/author")
    public List<AuthorDto> getAll() {
        return authorService.findAll().stream()
                .map(AuthorDto::toDto)
                .collect(Collectors.toList());
    }
}
