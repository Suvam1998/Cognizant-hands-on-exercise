package com.digitalnurture;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Exercise 3: Demonstrates the various JUnit assertions.
 */
public class AssertionsTest {

    @Test
    public void testAssertions() {
        // assertEquals — expected vs actual
        assertEquals(5, 2 + 3);

        // assertTrue / assertFalse — boolean conditions
        assertTrue(5 > 3);
        assertFalse(5 < 3);

        // assertNull / assertNotNull — reference checks
        assertNull(null);
        assertNotNull(new Object());
    }

    @Test
    public void testMoreAssertions() {
        // assertEquals with a message and floating-point delta
        assertEquals("2.0 / 4.0 should be 0.5", 0.5, 2.0 / 4.0, 0.0001);

        // assertArrayEquals — element-by-element array comparison
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});

        // assertSame / assertNotSame — reference identity, not just equality
        String s = "junit";
        assertSame(s, s);
        assertNotSame(new String("junit"), new String("junit"));
    }
}
