package com.coffeecode;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingTest {
    private static final Logger logger = LoggerFactory.getLogger(LoggingTest.class);

    @Test
    void testLogging() {
        logger.trace("Test trace message");
        logger.debug("Test debug message");
        logger.info("Test info message");
        logger.warn("Test warning message");
        logger.error("Test error message");
    }
}
