package com.digitalnurture;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.inOrder;

/**
 * Exercise 6: Verifying Interaction Order.
 * Use InOrder to assert methods were called in a specific sequence.
 */
class InteractionOrderTest {

    @Test
    void testInteractionOrder() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.performSequence("payload");

        // Verify order: connect -> saveData -> disconnect
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).connect();
        inOrder.verify(mockApi).saveData("payload");
        inOrder.verify(mockApi).disconnect();
    }
}
