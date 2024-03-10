//package ru.otus.spring.commands;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//import org.springframework.shell.standard.ShellOption;
//import ru.otus.spring.model.Book;
//import ru.otus.spring.service.impl.BookServiceImpl;
//
//import java.util.List;
//
//@ShellComponent
//@RequiredArgsConstructor
//public class BookCommands {
//
//    private final BookServiceImpl bookService;
//
//    @ShellMethod(value = "Add new book", key = {"ab", "add-book"})
//    public void addBook(@ShellOption String title,
//                        @ShellOption long authorId,
//                        @ShellOption long genreId) {
//        bookService.save(title, authorId, genreId);
//    }
//
//    @ShellMethod(value = "Find book by id", key = {"fb", "find-book"})
//    public void findById(@ShellOption Long id) {
//        Book book = bookService.findById(id);
//        bookService.print(List.of(book));
//    }
//
//    @ShellMethod(value = "Find all books", key = {"fbs", "find-all-books"})
//    public void findAll() {
//        bookService.print(bookService.findAll());
//    }
//
//    @ShellMethod(value = "Delete book by id", key = {"db", "delete-book"})
//    public void deleteById(@ShellOption Long id) {
//        bookService.deleteById(id);
//    }
//}
