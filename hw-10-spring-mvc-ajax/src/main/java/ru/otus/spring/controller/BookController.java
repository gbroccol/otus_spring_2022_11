package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.exceptions.NotFoundException;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.model.Book;
import ru.otus.spring.service.impl.BookServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

//GET /person/ - все пёрсоны
//GET /person/{id} – получение
//POST /person - создание
//PUT /person/{id} – изменение/замена
//PUT /person/{id}/account – изменение связанного
//PATCH /person/{id} – изменение/обновление
//DELETE /person/{id} - удаление

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookServiceImpl bookService;

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
    public BookDto getBookByTitleInRequest(@RequestParam("title") String title) {
        Book book = bookService.findByTitle(title).stream().findFirst().orElseThrow(NotFoundException::new);
        return BookDto.toDto(book);
    }

    @PostMapping(value = "/api/v1/book")
    public BookDto addBook(@RequestBody BookDto bookDto ) { // @Valid
        // ошибка если id не null
        var savedBook = bookService.save(bookDto.toDomainObject());
        return BookDto.toDto(savedBook);
    }

//    @PutMapping(value = "/api/v1/book/{id}")
//    public void updateBook(@RequestBody BookDto bookDto,
//                           @PathVariable("id") long id) {
////        bookService.save(bookDto);
//    }

//    @PatchMapping(value = "/api/v1/book/{id}")
//    public void updateBook(@RequestBody BookDto bookDto, @PathVariable("id") long id) {
////        bookService.save(bookDto);
//    }

    @DeleteMapping(value = "/api/v1/book/{id}")
    public void deleteBook(@PathVariable("id") long id) {
        bookService.deleteById(id);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.badRequest().body("Таких тут нет!");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("Invalid request body");
    }
}
