package com.coffeecode.model;

import java.util.SortedMap;
import java.util.TreeMap;

public class DictionaryData {
    private final TreeMap<String, String> englishToIndonesian = new TreeMap<>();
    private final TreeMap<String, String> indonesianToEnglish = new TreeMap<>();

    // Getter untuk membaca data terurut
    public SortedMap<String, String> getEnglishToIndonesian() {
        return englishToIndonesian;
    }

    public SortedMap<String, String> getIndonesianToEnglish() {
        return indonesianToEnglish;
    }

    // Metode untuk menambah entri ke English to Indonesian
    public void addEnglishToIndonesian(String english, String indonesian) {
        englishToIndonesian.put(english, indonesian);
    }

    // Metode untuk menambah entri ke Indonesian to English
    public void addIndonesianToEnglish(String indonesian, String english) {
        indonesianToEnglish.put(indonesian, english);
    }

    // Optional: Metode untuk mendapatkan terjemahan langsung
    public String translateEnglishToIndonesian(String word) {
        return englishToIndonesian.get(word);
    }

    public String translateIndonesianToEnglish(String word) {
        return indonesianToEnglish.get(word);
    }
}
