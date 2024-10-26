package ru.otus.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.exception.NotFoundException;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.impl.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    private static final Long BOOK_ID = 1L;
    private static final String BOOK_TITLE = "Мастер и Маргарита";
    private static final Author BOOK_AUTHOR = new Author(1L, "Михаил", "Булгаков");
    private static final Genre BOOK_GENRE = new Genre(4L, "фантастика");

    private static final Long BOOK_ID_2 = 2L;
    private static final String BOOK_TITLE_2 = "Идиот";
    private static final Author BOOK_AUTHOR_2 = new Author(1L, "Фёдор", "Достоевский");
    private static final Genre BOOK_GENRE_2 = new Genre(1L, "роман");

    private static final String ERROR_STRING = "Таких тут нет!";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BookService bookService;

    @Test
    void shouldReturnCorrectBooksList() throws Exception {
        List<Book> books = List.of(new Book(BOOK_ID, BOOK_TITLE, BOOK_AUTHOR, BOOK_GENRE, new ArrayList<>()),
                new Book(BOOK_ID_2, BOOK_TITLE_2, BOOK_AUTHOR_2, BOOK_GENRE_2, new ArrayList<>()));
        given(bookService.findAll()).willReturn(books);

        List<BookDto> expectedResult = books.stream()
                .map(BookDto::toDto).collect(Collectors.toList());

        mvc.perform(get("/api/v1/book"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldReturnCorrectBookById() throws Exception {
        Book book = new Book(BOOK_ID, BOOK_TITLE, BOOK_AUTHOR, BOOK_GENRE, new ArrayList<>());
        given(bookService.findById(BOOK_ID)).willReturn(book);

        BookDto expectedResult = BookDto.toDto(book);

        mvc.perform(get("/api/v1/book/" + BOOK_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldReturnCorrectBookByTitle() throws Exception {
        List<Book> books = List.of(new Book(BOOK_ID, BOOK_TITLE, BOOK_AUTHOR, BOOK_GENRE, new ArrayList<>()));
        given(bookService.findByTitle(BOOK_TITLE)).willReturn(books);

        List<BookDto> expectedResult = books.stream()
                .map(BookDto::toDto).collect(Collectors.toList());

        mvc.perform(get("/api/v1/book/").param("title", BOOK_TITLE))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldReturnAddedBook() throws Exception {
        Book savedBook = new Book(BOOK_ID, BOOK_TITLE, BOOK_AUTHOR, BOOK_GENRE, new ArrayList<>());
        given(bookService.save(any())).willReturn(savedBook);

        Book book = new Book(null, BOOK_TITLE, BOOK_AUTHOR, BOOK_GENRE, new ArrayList<>());
        String request = mapper.writeValueAsString(BookDto.toDto(book));

        BookDto expectedResult = BookDto.toDto(savedBook);
        mvc.perform(post("/api/v1/book/").contentType(APPLICATION_JSON).content(request))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldCorrectDeleteBook() throws Exception {
        mvc.perform(delete("/api/v1/book/1")).andExpect(status().isOk());
        verify(bookService, times(1)).deleteById(1L);
    }

    @Test
    void shouldReturnExpectedErrorWhenPersonNotFound() throws Exception {
        given(bookService.findById(BOOK_ID)).willThrow(new NotFoundException());
        given(bookService.findByTitle(BOOK_TITLE)).willThrow(new NotFoundException());

        mvc.perform(get("/api/v1/book/" + BOOK_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(ERROR_STRING));

        mvc.perform(get("/api/v1/book/").param("title", BOOK_TITLE))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(ERROR_STRING));
    }
}