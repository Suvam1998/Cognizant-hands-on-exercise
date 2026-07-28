package com.digitalnurture;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Exercise 4: Exception testing with assertThrows.
 */
class ExceptionThrowerTest {

    private final ExceptionThrower thrower = new ExceptionThrower();

    @Test
    void shouldThrowForNegativeValue() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> thrower.throwException(-1));
        // We can also assert on the exception message.
        assertEquals("Value must not be negative: -1", ex.getMessage());
    }

    @Test
    void shouldNotThrowForNonNegativeValue() {
        assertDoesNotThrow(() -> thrower.throwException(5));
        assertEquals(5, thrower.throwException(5));
    }
}
