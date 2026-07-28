package com.digitalnurture;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Exercise 1: Mocking Databases and Repositories.
 */
class ServiceTest {

    @Test
    void testServiceWithMockRepository() {
        // 1. Mock the repository.
        Repository mockRepository = mock(Repository.class);
        // 2. Stub it.
        when(mockRepository.getData()).thenReturn("Mock Data");

        // 3. Exercise the service and verify the logic.
        Service service = new Service(mockRepository);
        String result = service.processData();

        assertEquals("Processed Mock Data", result);
        verify(mockRepository).getData();
    }
}
