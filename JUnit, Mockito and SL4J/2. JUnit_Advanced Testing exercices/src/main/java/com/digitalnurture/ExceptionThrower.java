package com.digitalnurture;

/** Exercise 4 subject-under-test. */
public class ExceptionThrower {

    /**
     * Throws an IllegalArgumentException when value is negative,
     * otherwise returns the value.
     */
    public int throwException(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must not be negative: " + value);
        }
        return value;
    }
}
