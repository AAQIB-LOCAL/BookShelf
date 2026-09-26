package com.example.service;

import com.example.data.BookRepository;
import com.example.model.Book;
import java.util.List;

public class BookService {
    private final BookRepository repository;

    public BookService() {
        this.repository = new BookRepository();
    }

    public void addBook(String title, String author, String isbn) {
        String cleanTitle = normalize(title);
        String cleanAuthor = normalize(author);
        String cleanIsbn = normalize(isbn);

        if (cleanTitle.isEmpty() || cleanAuthor.isEmpty() || cleanIsbn.isEmpty()) {
            throw new IllegalArgumentException("Title, author and ISBN are required.");
        }

        if (repository.existsByIsbn(cleanIsbn)) {
            throw new IllegalArgumentException("A book with this ISBN already exists.");
        }

        repository.save(new Book(cleanTitle, cleanAuthor, cleanIsbn));
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim();
    }
}
