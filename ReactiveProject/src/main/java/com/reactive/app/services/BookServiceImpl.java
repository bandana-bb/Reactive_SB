package com.reactive.app.services;

import com.reactive.app.models.Book;
import com.reactive.app.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private  BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Mono<Book> create(Book book) {

        Mono<Book> createdBook = bookRepository.save(book).log();
        return createdBook;
    }

    @Override
    public Flux<Book> getAll() {
         return  bookRepository.findAll().delayElements(Duration.ofMillis(3000)).log().map(book->
         {
             book.setBookName(book.getBookName().toUpperCase());
             return book;
         });

    }

    @Override
    public Mono<Book> getById(int bookId) {
        Mono<Book> item = bookRepository.findById(bookId);
        return item;
    }

    @Override
    public Mono<Book> update(Book book, int bookId) {
        Mono<Book> oldBook = bookRepository.findById( bookId);
        return oldBook.flatMap(book1-> {
                    book1.setBookName(book.getBookName());
                    book1.setPublisher(book.getPublisher());
                    book1.setAuthor(book.getAuthor());
                    book1.setDescription(book.getDescription());
                    return bookRepository.save(book1).log();
                });
    }

    @Override
    public Mono<Void> delete(int bookId) {
         return  bookRepository.findById(bookId).
                 flatMap(book->
                bookRepository.delete(book));
    }

    @Override
    public Flux<Book> search(String query) {
        return null;
    }

    @Override
    public Flux<Book> searchBooks(String tittleKeyword) {
        return this.bookRepository.searchBooksByTitle("%"+tittleKeyword+"%");
    }
}
