package com.library.repository;

import org.springframework.stereotype.Repository;

/**
 * Data-access layer. Annotated with @Repository so it can be picked up by
 * component scanning (Exercise 6); it is also declared explicitly in
 * applicationContext.xml (Exercises 1/5).
 */
@Repository
public class BookRepository {

    /** Simulates fetching a book (with a little work, so AOP timing is visible). */
    public String getBookById(int id) {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Book#" + id + " - 'Spring in Action'";
    }
}
