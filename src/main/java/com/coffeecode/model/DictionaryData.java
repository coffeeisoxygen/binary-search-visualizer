package com.coffeecode.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link IDictionaryData} that stores and manages dictionary
 * entries.
 * Uses TreeMap for sorted storage of word pairs.
 */
public class DictionaryData implements IDictionaryData {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryData.class);
    private final TreeMap<String, String> dictionary;

    private DictionaryData(Builder builder) {
        this.dictionary = new TreeMap<>(builder.dictionary);
        LOGGER.info("DictionaryData instance created with {} entries", dictionary.size());
    }

    /**
     * Returns an unmodifiable view of the dictionary.
     *
     * @return a sorted map representing the dictionary.
     */
    @Override
    public SortedMap<String, String> getDictionary() {
        return Collections.unmodifiableSortedMap(dictionary);
    }

    /**
     * Translates a word from English to Indonesian or vice versa.
     *
     * @param word the word to translate.
     * @return the translated word, or null if not found.
     */
    @Override
    public String translate(String word) {
        LOGGER.debug("Translating word: {}", word);
        // Check word in both directions
        String direct = dictionary.get(word);
        if (direct != null) {
            LOGGER.debug("Found direct translation for word: {}", word);
            return direct;
        }
        // Find key by value
        String reverse = dictionary.entrySet().stream()
                .filter(entry -> entry.getValue().equals(word))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        if (reverse != null) {
            LOGGER.debug("Found reverse translation for word: {}", word);
        } else {
            LOGGER.warn("Translation not found for word: {}", word);
        }
        return reverse;
    }

    /**
     * Checks if the dictionary is valid (i.e., not empty).
     *
     * @return true if the dictionary is not empty, false otherwise.
     */
    @Override
    public boolean isValid() {
        boolean valid = !dictionary.isEmpty();
        LOGGER.info("Dictionary is valid: {}", valid);
        return valid;
    }

    /**
     * Returns a new Builder instance for constructing DictionaryData.
     *
     * @return a new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for creating DictionaryData instances.
     * Provides methods to add dictionary entries and build the final object.
     */
    public static class Builder {
        private final Map<String, String> dictionary = new HashMap<>();

        /**
         * Adds a word pair to the dictionary.
         *
         * @param english    the English word
         * @param indonesian the Indonesian translation
         * @return this Builder instance
         */
        public Builder addEntry(String english, String indonesian) {
            dictionary.put(english, indonesian);
            LOGGER.debug("Added entry to dictionary: {} -> {}", english, indonesian);
            return this;
        }

        /**
         * Builds and returns a new DictionaryData instance.
         *
         * @return a new DictionaryData instance
         */
        public DictionaryData build() {
            LOGGER.info("Building DictionaryData with {} entries", dictionary.size());
            return new DictionaryData(this);
        }
    }
}