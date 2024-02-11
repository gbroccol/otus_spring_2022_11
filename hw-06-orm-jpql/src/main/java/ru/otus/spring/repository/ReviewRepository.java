package ru.otus.spring.repository;

import ru.otus.spring.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Review save(Review review);
    Optional<Review> findById(long id);
    List<Review> findByBookId(long id);
    void update(Review review);
    void deleteById(long id);
}
