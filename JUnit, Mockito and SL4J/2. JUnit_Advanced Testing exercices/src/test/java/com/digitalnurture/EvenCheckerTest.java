package com.digitalnurture;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Exercise 1: Parameterized tests for EvenChecker.isEven — one test method runs
 * repeatedly with different inputs instead of many near-identical methods.
 */
class EvenCheckerTest {

    private final EvenChecker checker = new EvenChecker();

    // @ValueSource feeds each int in turn — all of these are even.
    @ParameterizedTest(name = "{0} is even")
    @ValueSource(ints = {0, 2, 4, 100, -8})
    void shouldReturnTrueForEvenNumbers(int number) {
        assertTrue(checker.isEven(number));
    }

    @ParameterizedTest(name = "{0} is odd")
    @ValueSource(ints = {1, 3, 5, 99, -7})
    void shouldReturnFalseForOddNumbers(int number) {
        assertFalse(checker.isEven(number));
    }

    // @CsvSource lets each case carry its own expected result.
    @ParameterizedTest(name = "isEven({0}) == {1}")
    @CsvSource({
            "2, true",
            "3, false",
            "10, true",
            "-5, false"
    })
    void shouldMatchExpected(int number, boolean expected) {
        org.junit.jupiter.api.Assertions.assertEquals(expected, checker.isEven(number));
    }
}
