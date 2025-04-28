package com.reactive.app.services;

import com.reactive.app.models.Book;
import com.reactive.app.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class RepositoryTest {
   @Autowired
    private BookRepository BookRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
   public void findMethodTest(){

//        Mono<Book> mononame = bookRepository.findByBookName("Educated");
//        StepVerifier.create(mononame).expectNextCount(1).verifyComplete();

//        Flux<Book> jamesClear = bookRepository.findByAuthor("James Clear");
//        StepVerifier.create(jamesClear).expectNextCount(1).verifyComplete();

        bookRepository.findByBookNameAndAuthor("Atomic Habits", "James Clear").
                as(StepVerifier::create).expectNextCount(1).verifyComplete();

    }
    @Test
    public void queryMethodTest(){
//        bookRepository.getAllBooksByAuthor("Educated","Tara Westover").as(StepVerifier::create).expectNextCount(1).verifyComplete();

        bookRepository.searchBooksByTitle("%Midnight %").as(StepVerifier::create).expectNextCount(1).verifyComplete();
    }



}

