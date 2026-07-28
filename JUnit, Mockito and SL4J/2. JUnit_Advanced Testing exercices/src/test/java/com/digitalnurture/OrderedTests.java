package com.digitalnurture;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 3: Controlling test execution order with @TestMethodOrder and @Order.
 *
 * By default JUnit 5 does not guarantee method order. @Order lets us run steps
 * in a deterministic sequence (lower @Order value runs first) — useful for
 * scenario-style tests. The shared counter proves the sequence.
 */
@TestMethodOrder(OrderAnnotation.class)
class OrderedTests {

    private static int step = 0;

    // Reset before the class runs so the sequence is correct whether this class
    // is executed directly or again as part of the AllTests suite.
    @BeforeAll
    static void reset() {
        step = 0;
    }

    @Test
    @Order(1)
    void firstTest() {
        step++;
        assertEquals(1, step);
        System.out.println("Running firstTest  (step=" + step + ")");
    }

    @Test
    @Order(2)
    void secondTest() {
        step++;
        assertEquals(2, step);
        System.out.println("Running secondTest (step=" + step + ")");
    }

    @Test
    @Order(3)
    void thirdTest() {
        step++;
        assertEquals(3, step);
        System.out.println("Running thirdTest  (step=" + step + ")");
    }
}
