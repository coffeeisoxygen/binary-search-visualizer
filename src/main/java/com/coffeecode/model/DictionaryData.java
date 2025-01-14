package com.coffeecode.model;

import java.util.SortedMap;
import java.util.TreeMap;

public class DictionaryData implements IDictionaryData {
    private final TreeMap<String, String> englishToIndonesian = new TreeMap<>();
    private final TreeMap<String, String> indonesianToEnglish = new TreeMap<>();

    // Getter untuk membaca data terurut
    @Override
    public SortedMap<String, String> getEnglishToIndonesian() {
        return englishToIndonesian;
    }

    @Override
    public SortedMap<String, String> getIndonesianToEnglish() {
        return indonesianToEnglish;
    }

    // Metode untuk menambah entri ke English to Indonesian
    @Override
    public void addEnglishToIndonesian(String english, String indonesian) {
        englishToIndonesian.put(english, indonesian);
    }

    // Metode untuk menambah entri ke Indonesian to English
    @Override
    public void addIndonesianToEnglish(String indonesian, String english) {
        indonesianToEnglish.put(indonesian, english);
    }

    // Optional: Metode untuk mendapatkan terjemahan langsung
    @Override
    public String translateEnglishToIndonesian(String word) {
        return englishToIndonesian.get(word);
    }

    @Override
    public String translateIndonesianToEnglish(String word) {
        return indonesianToEnglish.get(word);
    }
}
