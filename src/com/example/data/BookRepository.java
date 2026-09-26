package com.example.data;

import com.example.model.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookRepository {
    private final List<Book> books = new ArrayList<Book>();

    public synchronized void save(Book book) { books.add(book); }

    public synchronized boolean existsByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) return true;
        }
        return false;
    }

    public synchronized List<Book> findAll() {
        return Collections.unmodifiableList(new ArrayList<Book>(books));
    }
}
