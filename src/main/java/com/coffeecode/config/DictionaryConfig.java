package com.coffeecode.config;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeecode.exception.DictionaryException;

/**
 * Utility class for dictionary configuration settings.
 * Provides static methods for accessing dictionary paths and validation.
 */
public final class DictionaryConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryConfig.class);
    private static final String DICTIONARY_PATH = "src/main/resources/dictionary/english.json";

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class with only static methods.
     *
     * @throws UnsupportedOperationException if instantiation is attempted
     */
    private DictionaryConfig() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    /**
     * Gets the path to the dictionary data file.
     * 
     * @return String representing the path to the dictionary file
     * @throws DictionaryException if the dictionary file does not exist
     */
    public static String getDictionaryPath() {
        validatePath(DICTIONARY_PATH);
        return DICTIONARY_PATH;
    }

    /**
     * Validates the existence of the dictionary file at the given path.
     *
     * @param path the path to validate
     * @throws DictionaryException if the file does not exist
     */
    private static void validatePath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            LOGGER.error("Dictionary file not found: {}", path);
            throw new DictionaryException("Dictionary file not found: " + path);
        }
        LOGGER.info("Dictionary file found: {}", path);
    }
}
