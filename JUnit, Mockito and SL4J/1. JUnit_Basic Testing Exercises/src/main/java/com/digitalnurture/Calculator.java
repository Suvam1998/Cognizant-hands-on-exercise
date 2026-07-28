package com.digitalnurture;

/**
 * Exercise 2 subject-under-test: a simple class with a few methods to unit test.
 */
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * @throws ArithmeticException if b == 0
     */
    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    public boolean isEven(int n) {
        return n % 2 == 0;
    }
}
