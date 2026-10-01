package com.example.service;

import com.example.data.BookRepository;
import com.example.model.Book;
import java.util.ArrayList;
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
        validateRequired(cleanTitle, cleanAuthor, cleanIsbn);

        if (repository.existsByIsbn(cleanIsbn)) {
            throw new IllegalArgumentException("A book with this ISBN already exists.");
        }

        repository.save(new Book(cleanTitle, cleanAuthor, cleanIsbn));
    }

    public void updateBook(String isbn, String title, String author) {
        String cleanIsbn = normalize(isbn);
        String cleanTitle = normalize(title);
        String cleanAuthor = normalize(author);
        validateRequired(cleanTitle, cleanAuthor, cleanIsbn);

        if (repository.findByIsbn(cleanIsbn) == null) {
            throw new IllegalArgumentException("Book not found.");
        }

        repository.update(new Book(cleanTitle, cleanAuthor, cleanIsbn));
    }

    public void deleteBook(String isbn) {
        String cleanIsbn = normalize(isbn);
        if (cleanIsbn.isEmpty() || !repository.deleteByIsbn(cleanIsbn)) {
            throw new IllegalArgumentException("Book not found.");
        }
    }

    public List<Book> searchBooks(String query) {
        String cleanQuery = normalize(query).toLowerCase();
        if (cleanQuery.isEmpty()) return getAllBooks();

        List<Book> matches = new ArrayList<Book>();
        for (Book book : repository.findAll()) {
            if (book.getTitle().toLowerCase().contains(cleanQuery)
                    || book.getAuthor().toLowerCase().contains(cleanQuery)
                    || book.getIsbn().toLowerCase().contains(cleanQuery)) {
                matches.add(book);
            }
        }
        return matches;
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    private void validateRequired(String title, String author, String isbn) {
        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
            throw new IllegalArgumentException("Title, author and ISBN are required.");
        }
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim();
    }
}