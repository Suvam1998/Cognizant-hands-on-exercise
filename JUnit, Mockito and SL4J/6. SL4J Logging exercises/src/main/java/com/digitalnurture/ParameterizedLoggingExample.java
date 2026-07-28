package com.digitalnurture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 2: Parameterized logging with SLF4J.
 *
 * The {} placeholders are substituted only if the log statement is actually
 * emitted, which avoids the cost of string concatenation when the level is
 * disabled — the main advantage over "..." + value + "...".
 */
public class ParameterizedLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String user = "Alice";
        int itemCount = 3;
        double total = 149.95;

        // Single parameter
        logger.info("User {} logged in", user);

        // Multiple parameters
        logger.info("User {} has {} items in the cart", user, itemCount);

        // Mixed types
        logger.info("Order for {}: {} items, total ${}", user, itemCount, total);

        // Parameterized warning / error
        logger.warn("Cart for user {} is close to the limit ({} items)", user, itemCount);

        // Logging an exception: pass the Throwable as the LAST argument
        // (not as a {} placeholder) so the full stack trace is printed.
        try {
            int result = 10 / 0;
            logger.info("Result: {}", result);
        } catch (ArithmeticException ex) {
            logger.error("Failed to process order for user {}", user, ex);
        }
    }
}
