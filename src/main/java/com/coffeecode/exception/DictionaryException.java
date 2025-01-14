package com.coffeecode.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom exception class for dictionary-related errors.
 * Provides logging capabilities for error tracking.
 */
public class DictionaryException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryException.class);

    /**
     * Constructs a new DictionaryException with the specified message.
     *
     * @param message the error message
     */
    public DictionaryException(String message) {
        super(message);
        LOGGER.error(message);
    }

    /**
     * Constructs a new DictionaryException with the specified message and cause.
     *
     * @param message the error message
     * @param cause   the cause of the exception
     */
    public DictionaryException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.error(message, cause);
    }
}