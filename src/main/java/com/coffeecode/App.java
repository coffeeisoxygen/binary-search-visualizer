package com.coffeecode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 * 
 */
public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        testLogging();
    }

    private static void testLogging() {
        logger.info("=== Starting Logging Test ===");
        
        // Test all log levels
        logger.trace("1. Testing TRACE level");
        logger.debug("2. Testing DEBUG level");
        logger.info("3. Testing INFO level");
        logger.warn("4. Testing WARN level");
        logger.error("5. Testing ERROR level");

        // Test exception logging
        try {
            throw new RuntimeException("Test Exception");
        } catch (Exception e) {
            logger.error("6. Testing Exception logging", e);
        }

        logger.info("=== Logging Test Complete ===");
    }
}
