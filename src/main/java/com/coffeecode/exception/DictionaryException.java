
package com.coffeecode.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DictionaryException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryException.class);

    public DictionaryException(String message) {
        super(message);
        LOGGER.error(message);
    }

    public DictionaryException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.error(message, cause);
    }
}