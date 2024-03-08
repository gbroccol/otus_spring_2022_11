package ru.otus.spring.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.model.Review;
import ru.otus.spring.service.impl.ReviewService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ReviewCommands {

    private final ReviewService reviewService;

    @ShellMethod(value = "Add new review", key = {"ar", "add-review"})
    public void addReview(@ShellOption String message, long bookId) {
        reviewService.save(message, bookId);
    }

    @ShellMethod(value = "Find review by id", key = {"fr", "find-review"})
    public void findById(@ShellOption long id) {
        Review review = reviewService.findById(id);
        reviewService.print(List.of(review));
    }

    @ShellMethod(value = "Find all reviews by bookId", key = {"frs", "find-all-reviews-by-book-id"})
    public void findAll(@ShellOption long bookId) {
        reviewService.print(reviewService.findByBookId(bookId));
    }

    @ShellMethod(value = "Delete review by id", key = {"dr", "delete-review"})
    public void deleteById(@ShellOption long id) {
        reviewService.deleteById(id);
    }
}
