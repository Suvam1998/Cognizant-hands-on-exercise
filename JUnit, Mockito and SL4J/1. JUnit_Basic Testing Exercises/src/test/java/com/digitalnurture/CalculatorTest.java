package com.digitalnurture;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Exercise 2: Basic JUnit tests for the Calculator methods.
 */
public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(-1, calculator.add(2, -3));
    }

    @Test
    public void testSubtract() {
        assertEquals(1, calculator.subtract(4, 3));
    }

    @Test
    public void testMultiply() {
        assertEquals(12, calculator.multiply(4, 3));
        assertEquals(0, calculator.multiply(4, 0));
    }

    @Test
    public void testDivide() {
        assertEquals(2, calculator.divide(6, 3));
    }

    @Test
    public void testIsEven() {
        assertTrue(calculator.isEven(4));
        assertFalse(calculator.isEven(5));
    }

    /** Expecting an exception when dividing by zero. */
    @Test(expected = ArithmeticException.class)
    public void testDivideByZeroThrows() {
        calculator.divide(10, 0);
    }
}
