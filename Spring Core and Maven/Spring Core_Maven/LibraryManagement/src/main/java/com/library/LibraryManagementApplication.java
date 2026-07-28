package com.library;

import com.library.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Loads the Spring context from applicationContext.xml and exercises the
 * configured beans (DI + AOP).
 *
 * To run the annotation-based configuration (Exercise 6) instead, change the
 * file name below to "applicationContext-annotation.xml".
 */
public class LibraryManagementApplication {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("applicationContext.xml")) {

            BookService bookService = context.getBean("bookService", BookService.class);

            System.out.println("Repository injected? "
                    + (bookService.getBookRepository() != null));
            System.out.println("Result: " + bookService.getBookDetails(1));
            System.out.println("Result: " + bookService.getBookDetails(2));
        }
    }
}
