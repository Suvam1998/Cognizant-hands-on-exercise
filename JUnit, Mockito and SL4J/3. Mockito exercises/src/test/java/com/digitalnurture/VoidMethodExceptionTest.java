package com.digitalnurture;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * Exercise 7: Handling Void Methods with Exceptions.
 * Stub a void method to throw, then assert the exception propagates and the
 * interaction happened.
 */
class VoidMethodExceptionTest {

    @Test
    void testVoidMethodThrows() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub the void method to throw when called with "bad".
        doThrow(new RuntimeException("save failed"))
                .when(mockApi).saveData("bad");

        MyService service = new MyService(mockApi);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.saveData("bad"));
        assertEquals("save failed", ex.getMessage());

        // The void method was still invoked.
        verify(mockApi).saveData("bad");
    }
}
