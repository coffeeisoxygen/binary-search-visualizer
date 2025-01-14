package com.coffeecode.config;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeecode.exception.DictionaryException;

/**
 * Configuration class for dictionary settings.
 * This class provides the path to the dictionary file and validates its
 * existence.
 * It is a utility class and cannot be instantiated.
 */
public final class DictionaryConfig {

    /**
     * Logger instance for logging information and errors.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryConfig.class);

    /**
     * Path to the dictionary file.
     */
    private static final String DICTIONARY_PATH = "src/main/resources/dictionary/english.json";

    /**
     * Private constructor to prevent instantiation.
     * Throws {@link UnsupportedOperationException} if called.
     */
    private DictionaryConfig() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Returns the path to the dictionary file.
     * Validates the existence of the file before returning the path.
     *
     * @return the path to the dictionary file.
     * @throws DictionaryException if the dictionary file does not exist.
     */
    public static String getDictionaryPath() {
        validatePath(DICTIONARY_PATH);
        return DICTIONARY_PATH;
    }

    /**
     * Validates the existence of the dictionary file at the given path.
     * Logs an error and throws {@link DictionaryException} if the file does not
     * exist.
     * Logs an info message if the file is found.
     *
     * @param path the path to the dictionary file.
     * @throws DictionaryException if the dictionary file does not exist.
     */
    private static void validatePath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            LOGGER.error("Dictionary file not found: {}", path);
            throw new DictionaryException("Dictionary file not found: " + path);
        } else {
            LOGGER.info("Dictionary file found: {}", path);
        }
    }
}
