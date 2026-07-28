package com.digitalnurture;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Exercise 5: Mocking and Stubbing with Multiple Returns.
 * Stub a method to return different values on consecutive calls.
 */
class MultipleReturnsTest {

    @Test
    void testConsecutiveReturns() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Different value on each consecutive call.
        when(mockApi.getData()).thenReturn("First", "Second", "Third");

        MyService service = new MyService(mockApi);

        assertEquals("First", service.fetchData());
        assertEquals("Second", service.fetchData());
        assertEquals("Third", service.fetchData());
        // After the list is exhausted, the last value keeps being returned.
        assertEquals("Third", service.fetchData());
    }
}
