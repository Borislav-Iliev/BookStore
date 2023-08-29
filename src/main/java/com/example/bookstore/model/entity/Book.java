package com.example.bookstore.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private String publisher;

    @Column(name = "date_added", nullable = false)
    private LocalDateTime dateAdded;

    @ManyToMany
    @JoinTable(name = "book_genre",
            joinColumns = @JoinColumn(name = "book_isbn", referencedColumnName = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    List<Genre> genres;

    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_isbn", referencedColumnName = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    List<Author> authors;

    public Book() {
        this.genres = new ArrayList<>();
        this.genres = new ArrayList<>();
    }

    public Book(String isbn, String title, int year, String publisher, LocalDateTime dateAdded, List<Genre> genres, List<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.publisher = publisher;
        this.dateAdded = dateAdded;
        this.genres = genres;
        this.authors = authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Book setYear(int year) {
        this.year = year;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public Book setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public Book setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public Book setGenres(List<Genre> genres) {
        this.genres = genres;
        return this;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Book setAuthors(List<Author> authors) {
        this.authors = authors;
        return this;
    }
}
