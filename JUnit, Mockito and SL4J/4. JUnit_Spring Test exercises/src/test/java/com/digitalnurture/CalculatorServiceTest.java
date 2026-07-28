package com.digitalnurture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 1: Basic unit test for a service method.
 * Exercise 9: Parameterized test with multiple inputs.
 *
 * CalculatorService has no dependencies, so a plain unit test (no Spring
 * context) is the fastest and most appropriate choice.
 */
class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    // Exercise 1
    @Test
    void testAdd() {
        assertEquals(5, calculatorService.add(2, 3));
    }

    // Exercise 9
    @ParameterizedTest(name = "add({0}, {1}) == {2}")
    @CsvSource({
            "2, 3, 5",
            "0, 0, 0",
            "-1, 1, 0",
            "100, 200, 300",
            "-5, -7, -12"
    })
    void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, calculatorService.add(a, b));
    }
}
