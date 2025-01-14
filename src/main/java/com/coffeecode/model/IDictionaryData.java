package com.coffeecode.model;

import java.util.SortedMap;

public interface IDictionaryData {
    // Read-only operations
    SortedMap<String, String> getEnglishToIndonesian();

    SortedMap<String, String> getIndonesianToEnglish();

    String translateEnglishToIndonesian(String word);

    String translateIndonesianToEnglish(String word);

    boolean isValid();
}