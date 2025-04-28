package com.reactive.app.repositories;

import com.reactive.app.models.Book;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {


    Mono<Book> findByBookName(String bookName);

    Flux<Book> findByAuthor(String author);

    Flux<Book> findByPublisher(String publisher);

    Flux<Book> findByBookNameAndAuthor(String bookName, String author);

    @Query("Select * from book_details where book_name = :name and author= :auth")
    Flux<Book> getAllBooksByAuthor(String name,String auth);

    @Query("select * from book_details where book_name like :title")
    Flux<Book> searchBooksByTitle(String title);


}
