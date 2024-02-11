package ru.otus.spring.service.impl;

import ru.otus.spring.model.Review;
import java.util.List;

public interface ReviewService {
   void save(String message, long bookId);
   Review findById(long id);
   List<Review> findByBookId(long bookId);
   void deleteById(long id);
   void print(List<Review> reviews);
}
