package com.coffeecode.config;

import java.io.File;

import com.coffeecode.exception.DictionaryException;

/*
 * The DictionaryConfig class provides configuration paths for dictionary files.
 * It contains constants for the paths to the English and Indonesian dictionary
 * files,
 * and provides methods to retrieve these paths with validation.
 * 
 * This class is final and cannot be instantiated.
 */

public final class DictionaryConfig {
    private static final String DICTIONARY_PATH = "src/main/resources/dictionary/english.json";

    // Prevent instantiation
    private DictionaryConfig() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String getDictionaryPath() {
        validatePath(DICTIONARY_PATH);
        return DICTIONARY_PATH;
    }

    private static void validatePath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new DictionaryException("Dictionary file not found: " + path);
        }
    }
}