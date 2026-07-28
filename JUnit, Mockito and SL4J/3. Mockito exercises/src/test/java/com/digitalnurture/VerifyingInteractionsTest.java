package com.digitalnurture;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Exercise 2: Verifying Interactions.
 * Verify that the service actually called the external API.
 */
class VerifyingInteractionsTest {

    @Test
    void testVerifyInteraction() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData();

        // Verify getData() was called (exactly once).
        verify(mockApi).getData();
        verify(mockApi, times(1)).getData();
    }
}
