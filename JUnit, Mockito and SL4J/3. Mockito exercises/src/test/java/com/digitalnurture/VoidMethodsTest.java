package com.digitalnurture;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * Exercise 4: Handling Void Methods.
 * Stub a void method with doNothing() and verify it was invoked with the
 * expected argument.
 */
class VoidMethodsTest {

    @Test
    void testVoidMethod() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub the void method (doNothing is the default for mocks, shown here
        // explicitly for clarity).
        doNothing().when(mockApi).saveData("hello");

        MyService service = new MyService(mockApi);
        service.saveData("hello");

        // Verify the void method was called with the expected argument.
        verify(mockApi).saveData("hello");
    }
}
