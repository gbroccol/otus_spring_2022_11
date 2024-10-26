package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.exception.NotFoundException;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.model.Book;
import ru.otus.spring.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

// REST examples API
// GET /api/v1/book/ - все книги
// GET /book/{id} – получение
// POST /book - создание
// PUT /book/{id} – изменение/замена
// PUT /book/{id}/photo – изменение связанного
// PATCH /book/{id} – изменение/обновление
// DELETE /book/{id} - удаление

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "/api/v1/book")
    public List<BookDto> getAllBooks() {
        return bookService.findAll().stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v1/book/{id}")
    public BookDto getBookByIdInPath(@PathVariable("id") long id) {
        Book book = bookService.findById(id);
        return BookDto.toDto(book);
    }

    @GetMapping(value = "/api/v1/book", params = "title")
    public List<BookDto>  getBookByTitleInRequest(@RequestParam("title") String title) {
        return bookService.findByTitle(title).stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/api/v1/book")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto dto) {
        var savedBook = bookService.save(dto.toDomainObject());
        return new ResponseEntity<>(
                BookDto.toDto(savedBook), HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/v1/book/{id}")
    public void deleteBook(@PathVariable("id") long id) {
        bookService.deleteById(id);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.badRequest().body("Таких тут нет!");
    }
}
