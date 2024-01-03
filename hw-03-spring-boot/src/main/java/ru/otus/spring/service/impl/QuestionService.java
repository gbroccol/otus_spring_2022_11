package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.Question;
import ru.otus.spring.repository.QuestionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService implements ServiceCRUD<Question> {

    private final QuestionRepository repository;

    @Override
    public List<Question> findAll() {
        return repository.findAll();
    }
}
