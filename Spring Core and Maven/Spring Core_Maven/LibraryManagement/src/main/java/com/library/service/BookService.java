package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business layer. Supports BOTH injection styles (Exercise 7):
 *  - constructor injection of BookRepository,
 *  - setter injection of BookRepository and of the catalogName property.
 *
 * @Service / @Autowired make it work with annotation-based config (Exercise 6);
 * the same class is also wired explicitly via XML (Exercises 1/2/5/7).
 */
@Service
public class BookService {

    private BookRepository bookRepository;
    private String catalogName = "Central Library";

    public BookService() {
    }

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /** Setter injection for BookRepository (Exercises 2 & 7). */
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /** Setter injection for a simple property (demonstrates setter injection). */
    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public String getBookDetails(int id) {
        return "[" + catalogName + "] " + bookRepository.getBookById(id);
    }
}
