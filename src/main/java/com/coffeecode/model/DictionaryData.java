package com.coffeecode.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class DictionaryData implements IDictionaryData {
    private final TreeMap<String, String> englishToIndonesian;
    private final TreeMap<String, String> indonesianToEnglish;

    private DictionaryData(Builder builder) {
        this.englishToIndonesian = new TreeMap<>(builder.englishToIndonesian);
        this.indonesianToEnglish = new TreeMap<>(builder.indonesianToEnglish);
    }

    @Override
    public SortedMap<String, String> getEnglishToIndonesian() {
        return Collections.unmodifiableSortedMap(englishToIndonesian);
    }

    @Override
    public SortedMap<String, String> getIndonesianToEnglish() {
        return Collections.unmodifiableSortedMap(indonesianToEnglish);
    }

    @Override
    public String translateEnglishToIndonesian(String word) {
        return englishToIndonesian.get(word);
    }

    @Override
    public String translateIndonesianToEnglish(String word) {
        return indonesianToEnglish.get(word);
    }

    @Override
    public boolean isValid() {
        return !englishToIndonesian.isEmpty() && !indonesianToEnglish.isEmpty();
    }

    // Builder remains the same
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Map<String, String> englishToIndonesian = new HashMap<>();
        private final Map<String, String> indonesianToEnglish = new HashMap<>();

        public Builder addEntry(String english, String indonesian) {
            englishToIndonesian.put(english, indonesian);
            indonesianToEnglish.put(indonesian, english);
            return this;
        }

        public DictionaryData build() {
            return new DictionaryData(this);
        }
    }
}