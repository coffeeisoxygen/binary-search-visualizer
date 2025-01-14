package com.coffeecode.model;

import java.util.SortedMap;

public interface IDictionaryData {

    // Getter untuk membaca data terurut
    SortedMap<String, String> getEnglishToIndonesian();

    SortedMap<String, String> getIndonesianToEnglish();

    // Metode untuk menambah entri ke English to Indonesian
    void addEnglishToIndonesian(String english, String indonesian);

    // Metode untuk menambah entri ke Indonesian to English
    void addIndonesianToEnglish(String indonesian, String english);

    // Optional: Metode untuk mendapatkan terjemahan langsung
    String translateEnglishToIndonesian(String word);

    String translateIndonesianToEnglish(String word);

}