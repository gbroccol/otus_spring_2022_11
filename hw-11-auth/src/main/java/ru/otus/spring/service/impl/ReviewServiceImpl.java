package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.exception.BookNotFoundException;
import ru.otus.spring.model.Review;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.ReviewRepository;
import ru.otus.spring.service.ReviewService;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void save(String message, long bookId) {
        try {
            var book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new BookNotFoundException(MessageFormat.format("Review is not created. No book_id = {0}.", bookId)));
            var review = new Review(null, message, book);
            reviewRepository.save(review);
        } finally {
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Review findById(long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Review> findByBookId(long bookId) {
        return reviewRepository.findByBook(bookId);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        reviewRepository.deleteById(id);
    }
}
