package com.coffeecode.model;

import java.util.SortedMap;

public interface IDictionaryData {
    // Single map access
    SortedMap<String, String> getDictionary();

    // Translation methods
    String translate(String word);

    // Validation
    boolean isValid();
}