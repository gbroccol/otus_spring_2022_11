package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.exception.BookNotExistsException;
import ru.otus.spring.model.Review;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.ReviewRepository;
import ru.otus.spring.service.OutService;
import ru.otus.spring.util.Color;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final OutService outService;

    @Override
    public void save(String message, long bookId) {
        try {
            var book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new BookNotExistsException(MessageFormat.format("Review is not created. No book_id = {0}.", bookId)));
            var review = new Review(0, message, book);
            reviewRepository.save(review);
        } catch (BookNotExistsException e) {
            outService.outputStringNextLine(Color.ANSI_RED + e.getMessage() + Color.ANSI_RESET);
        }
    }

    @Override
    public Review findById(long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public List<Review> findByBookId(long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    @Override
    public void deleteById(long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public void print(List<Review> reviews) {
        for (Review review : reviews) {
            outService.outputStringNextLine(
                    "id = " + review.getReviewId() +
                            "\t | message = " + review.getMessage());
        }
    }
}
