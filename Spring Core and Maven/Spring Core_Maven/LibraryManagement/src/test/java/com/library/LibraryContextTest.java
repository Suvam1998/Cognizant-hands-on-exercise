package com.library;

import com.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LibraryContextTest {

    @Test
    void xmlContextWiresDependencyAndAppliesAop() {
        try (ClassPathXmlApplicationContext ctx =
                     new ClassPathXmlApplicationContext("applicationContext.xml")) {

            BookService service = ctx.getBean("bookService", BookService.class);

            // DI (Exercises 1/2/5/7): repository injected
            assertNotNull(service.getBookRepository());

            // AOP (Exercises 3/8): the bean is an AOP proxy
            assertTrue(AopUtils.isAopProxy(service), "BookService should be an AOP proxy");

            // Behaviour works end-to-end (advice runs around this call)
            String details = service.getBookDetails(1);
            assertTrue(details.contains("Central Library"));
            assertTrue(details.contains("Book#1"));
        }
    }

    @Test
    void annotationContextDiscoversBeans() {
        try (ClassPathXmlApplicationContext ctx =
                     new ClassPathXmlApplicationContext("applicationContext-annotation.xml")) {

            BookService service = ctx.getBean(BookService.class);
            assertNotNull(service.getBookRepository());
            assertTrue(AopUtils.isAopProxy(service));
            assertTrue(service.getBookDetails(2).contains("Book#2"));
        }
    }
}
