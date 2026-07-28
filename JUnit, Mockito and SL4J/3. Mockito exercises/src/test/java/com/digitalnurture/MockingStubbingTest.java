package com.digitalnurture;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Exercise 1: Mocking and Stubbing.
 * Create a mock, stub a method to return a predefined value, and test the
 * service that uses it.
 */
class MockingStubbingTest {

    @Test
    void testExternalApi() {
        // 1. Create a mock object for the external API.
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // 2. Stub the method to return a predefined value.
        when(mockApi.getData()).thenReturn("Mock Data");

        // 3. Use the mock through the service.
        MyService service = new MyService(mockApi);
        String result = service.fetchData();

        assertEquals("Mock Data", result);
    }
}
