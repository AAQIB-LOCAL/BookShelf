package com.example.data;

import com.example.model.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookRepository {
    private final List<Book> books = new ArrayList<Book>();

    public synchronized void save(Book book) { books.add(book); }

    public synchronized boolean existsByIsbn(String isbn) {
        return findByIsbn(isbn) != null;
    }

    public synchronized Book findByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) return book;
        }
        return null;
    }

    public synchronized void update(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equalsIgnoreCase(updatedBook.getIsbn())) {
                books.set(i, updatedBook);
                return;
            }
        }
    }

    public synchronized boolean deleteByIsbn(String isbn) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equalsIgnoreCase(isbn)) {
                books.remove(i);
                return true;
            }
        }
        return false;
    }

    public synchronized List<Book> findAll() {
        return Collections.unmodifiableList(new ArrayList<Book>(books));
    }
}