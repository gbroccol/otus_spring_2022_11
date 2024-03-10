package ru.otus.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

//    public static final String ERROR_STRING = "Таких тут нет!";
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private ObjectMapper mapper;
//
//    @MockBean
//    private PersonRepository repository;
//
//    @Test
//    void shouldReturnCorrectPersonsList() throws Exception {
//        List<Person> persons = List.of(new Person(1, "Person1"), new Person(2, "Person2"));
//        given(repository.findAll()).willReturn(persons);
//
//        List<PersonDto> expectedResult = persons.stream()
//                .map(PersonDto::toDto).collect(Collectors.toList());
//
//        mvc.perform(get("/persons"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
//    }
//
//    @Test
//    void shouldReturnCorrectPersonByNameInRequest() throws Exception {
//        Person person = new Person(1, "Person1");
//        given(repository.findByName(person.getName())).willReturn(List.of(person));
//        PersonDto expectedResult = PersonDto.toDto(person);
//
//        mvc.perform(get("/persons").param("name", person.getName()))
//                .andExpect(status().isOk())
//                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
//    }
//
//    @Test
//    void shouldReturnCorrectPersonByIdInPath() throws Exception {
//        Person person = new Person(1, "Person1");
//        given(repository.findById(1L)).willReturn(Optional.of(person));
//        PersonDto expectedResult = PersonDto.toDto(person);
//
//        mvc.perform(get("/persons/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
//    }
//
//    @Test
//    void shouldReturnExpectedErrorWhenPersonNotFound() throws Exception {
//        given(repository.findById(1L)).willReturn(Optional.empty());
//
//        mvc.perform(get("/persons").param("name", "Person1"))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string(ERROR_STRING));
//
//        mvc.perform(get("/persons/1"))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string(ERROR_STRING));
//    }
}