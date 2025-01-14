package com.coffeecode;

import com.coffeecode.loader.IDictionaryLoader;
import com.coffeecode.loader.JsonLoader;
import com.coffeecode.model.IDictionaryData;

public class App {
    public static void main(String[] args) {
        try {
            IDictionaryLoader loader = new JsonLoader();
            IDictionaryData dictionary = loader.loadDictionaries();

            // Display translations directly
            System.out.println("English to Indonesian:");
            dictionary.getEnglishToIndonesian()
                    .forEach((k, v) -> System.out.println(k + " -> " + v));

            // Test translations
            System.out.println("\nTest translations:");
            System.out.println("'cat' in Indonesian: " +
                    dictionary.translateEnglishToIndonesian("cat"));
            System.out.println("'buku' in English: " +
                    dictionary.translateIndonesianToEnglish("buku"));

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}