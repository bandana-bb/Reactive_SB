package com.reactive.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.processing.Generated;


@Table(name="book_details")
public class Book {

    @Id
    @Column("book_id")
    private int bookId;
    @Column("book_name")
    private String bookName;
    @Column("book_desc")
    private String description;
    private String publisher;
    private String author;

    public Book(int bookId, String bookName, String description, String publisher, String author) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.description = description;
        this.publisher = publisher;
        this.author = author;
    }
    public Book(){

    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
