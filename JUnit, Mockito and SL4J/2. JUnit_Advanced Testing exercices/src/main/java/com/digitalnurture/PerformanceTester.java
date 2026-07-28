package com.digitalnurture;

/** Exercise 5 subject-under-test. */
public class PerformanceTester {

    /**
     * Simulates a unit of work that takes roughly the given number of
     * milliseconds.
     */
    public void performTask(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /** A fast computation used to assert an operation finishes in time. */
    public long sumTo(int n) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
}
