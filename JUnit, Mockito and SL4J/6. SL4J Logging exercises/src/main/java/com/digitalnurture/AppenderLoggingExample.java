package com.digitalnurture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 3: Using different appenders.
 *
 * The logback.xml on the classpath routes every log event to BOTH a console
 * appender and a file appender (app.log). Running this class writes the same
 * lines to the terminal and to the file.
 */
public class AppenderLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(AppenderLoggingExample.class);

    public static void main(String[] args) {
        logger.debug("Debug: application starting up");
        logger.info("Info: processing records");
        logger.warn("Warn: low disk space");
        logger.error("Error: failed to reach the database");
        logger.info("These lines are written to BOTH the console and app.log");
    }
}
