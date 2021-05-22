package com.booklib.model;

import java.sql.Timestamp;

public class Book {
    private int book_id;
    private String title;
    private String description;
    private String price;
    private int author_id;
    private Timestamp createdAt;
    private Author author;

    public Book() {
    }

    public Book(String title, String description, String price, int author_id) {
        super();
        this.title = title;
        this.description = description;
        this.price = price;
        this.author_id = author_id;
    }

    public Book(int book_id, String title, String description, String price, int author_id) {
        this(title,description,price,author_id);
        this.book_id = book_id;
    }

    public Book(int book_id, String title, String description, String price, int author_id, Timestamp createdAt) {
        this(book_id,title,description,price,author_id);
        this.createdAt = createdAt;
    }
    
    
    public Book(int book_id, String title, String description, String price, int author_id, Timestamp createdAt, Author author) {
        this(book_id,title,description,price,author_id,createdAt);
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
}
