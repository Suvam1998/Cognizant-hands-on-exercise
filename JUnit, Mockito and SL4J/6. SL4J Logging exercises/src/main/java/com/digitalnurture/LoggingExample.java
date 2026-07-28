package com.digitalnurture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 1: Logging error messages and warning levels with SLF4J.
 */
public class LoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        logger.error("This is an error message");
        logger.warn("This is a warning message");
        // The other levels, for completeness:
        logger.info("This is an info message");
        logger.debug("This is a debug message");
        logger.trace("This is a trace message");
    }
}
