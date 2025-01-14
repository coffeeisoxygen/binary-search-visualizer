package com.coffeecode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        try {
            logger.info("Starting application...");
            testLogging();
            logger.info("Application completed successfully");
        } catch (Exception e) {
            logger.error("Application failed", e);
        } finally {
            shutdownGracefully();
        }
    }

    private static void shutdownGracefully() {
        try {
            logger.info("Shutting down executor service...");
            executor.shutdown();
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private static void testLogging() {
        logger.info("Testing logging functionality");
        try {
            throw new RuntimeException("Test Exception");
        } catch (Exception e) {
            logger.error("Caught test exception", e);
        }
    }
}
