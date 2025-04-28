package com.reactive.app.Controller;

import com.reactive.app.models.Book;
import com.reactive.app.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //create
    @PostMapping
    public Mono<Book> create(@RequestBody Book book) {
        return bookService.create(book);

    }

    //getAll
    @GetMapping
    public Flux<Book> getAll() {
        return bookService.getAll();
    }

    //getsingle

    @GetMapping("/{bookId}")
    public Mono<Book> getById(@PathVariable("bookId") int bid) {
        return bookService.getById(bid);
    }

    @PutMapping("/{bookId}")
    public Mono<Book> update(@RequestBody Book book,@PathVariable int bookId) {
        return bookService.update(book,bookId);
    }

    @DeleteMapping("/{bookId}")
    public Mono<Void> deleteById(@PathVariable int bookId) {
        return bookService.delete(bookId);
    }

    @GetMapping("/search")
    public Flux<Book> searchBooks(@RequestParam("query") String query) {
        System.out.println(query);
        return this.bookService.searchBooks(query);

    }

}
