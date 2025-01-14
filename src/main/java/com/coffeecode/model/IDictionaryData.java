package com.coffeecode.model;

import java.util.Map;

/**
 * Interface representing dictionary data operations.
 * Defines the contract for accessing and manipulating dictionary entries.
 */
public interface IDictionaryData {
    /**
     * Gets the complete dictionary mapping.
     * 
     * @return A Map containing word pairs (key: word in one language, value:
     *         translation)
     */
    Map<String, String> getDictionary();

    /**
     * Translates a given word.
     * 
     * @param word The word to translate
     * @return The translated word, or null if no translation exists
     */
    String translate(String word);

    /**
     * Checks if the dictionary data is valid.
     * 
     * @return true if the dictionary data is valid, false otherwise
     */
    boolean isValid();
}