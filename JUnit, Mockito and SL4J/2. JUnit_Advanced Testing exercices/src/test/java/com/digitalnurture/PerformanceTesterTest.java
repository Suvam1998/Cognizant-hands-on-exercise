package com.digitalnurture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

/**
 * Exercise 5: Timeout / performance testing.
 * Shows both the @Timeout annotation and the assertTimeout assertion.
 */
class PerformanceTesterTest {

    private final PerformanceTester tester = new PerformanceTester();

    /** Fails if the test does not finish within 500 ms. */
    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void taskShouldFinishWithinTimeout() {
        tester.performTask(100);   // well under the 500 ms budget
    }

    /** assertTimeout runs the code and fails if it overruns the duration. */
    @Test
    void computationShouldBeFast() {
        long result = assertTimeout(Duration.ofMillis(200),
                () -> tester.sumTo(1_000_000));
        assertEquals(500_000_500_000L, result);
    }
}
