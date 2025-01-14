package com.coffeecode.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class DictionaryData implements IDictionaryData {
    private final TreeMap<String, String> dictionary;

    private DictionaryData(Builder builder) {
        this.dictionary = new TreeMap<>(builder.dictionary);
    }

    @Override
    public SortedMap<String, String> getDictionary() {
        return Collections.unmodifiableSortedMap(dictionary);
    }

    @Override
    public String translate(String word) {
        // Check word in both directions
        String direct = dictionary.get(word);
        if (direct != null) {
            return direct;
        }
        // Find key by value
        return dictionary.entrySet().stream()
                .filter(entry -> entry.getValue().equals(word))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean isValid() {
        return !dictionary.isEmpty();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Map<String, String> dictionary = new HashMap<>();

        public Builder addEntry(String english, String indonesian) {
            dictionary.put(english, indonesian);
            return this;
        }

        public DictionaryData build() {
            return new DictionaryData(this);
        }
    }
}