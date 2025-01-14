package com.coffeecode;

import com.coffeecode.loader.JsonLoader;
import com.coffeecode.model.DictionaryData;

public class App {
    public static void main(String[] args) {
        DictionaryData dictionary = null;
        try {
            dictionary = JsonLoader.loadDictionaries();

            System.out.println("English to Indonesian:");
            dictionary.getEnglishToIndonesian().forEach((k, v) -> System.out.println(k + " -> " + v));

            System.out.println("\nIndonesian to English:");
            dictionary.getIndonesianToEnglish().forEach((k, v) -> System.out.println(k + " -> " + v));

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        if (dictionary != null) {
            System.out.println("test find word in dictionary");
            System.out.println("find 'Cat' in dictionary: " + dictionary.translateEnglishToIndonesian("cat"));
            System.out.println("find 'buku' in dictionary: " + dictionary.translateIndonesianToEnglish("buku"));
        }
    }
}
