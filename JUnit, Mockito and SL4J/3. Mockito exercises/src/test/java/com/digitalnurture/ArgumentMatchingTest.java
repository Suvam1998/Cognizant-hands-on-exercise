package com.digitalnurture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Exercise 3: Argument Matching.
 * Uses matchers (anyInt, eq) both when stubbing and when verifying.
 * Shows the @Mock / MockitoExtension style of creating mocks.
 */
@ExtendWith(MockitoExtension.class)
class ArgumentMatchingTest {

    @Mock
    private ExternalApi mockApi;

    @Test
    void testArgumentMatchers() {
        // Stub for ANY int argument.
        when(mockApi.getDataById(anyInt())).thenReturn("Generic");
        // Stub for a SPECIFIC argument (eq) — more specific stub wins.
        when(mockApi.getDataById(eq(42))).thenReturn("Answer");

        MyService service = new MyService(mockApi);

        assertEquals("Answer", service.fetchDataById(42));
        assertEquals("Generic", service.fetchDataById(7));

        // Verify with matchers.
        verify(mockApi).getDataById(eq(42));          // the specific call, once
        verify(mockApi).getDataById(eq(7));           // the other specific call, once
        verify(mockApi, times(2)).getDataById(anyInt()); // both calls in total
    }
}
