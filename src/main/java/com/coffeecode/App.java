package com.coffeecode;

import com.coffeecode.loader.DictionaryLoader;
import com.coffeecode.loader.JsonLoader;
import com.coffeecode.model.IDictionaryData;

public class App {
    public static void main(String[] args) {
        DictionaryLoader loader = new JsonLoader();
        IDictionaryData dictionary = null;

        try {
            dictionary = loader.loadDictionaries();

            System.out.println("English to Indonesian:");
            dictionary.getEnglishToIndonesian()
                    .forEach((k, v) -> System.out.println(k + " -> " + v));

            System.out.println("\nIndonesian to English:");
            dictionary.getIndonesianToEnglish()
                    .forEach((k, v) -> System.out.println(k + " -> " + v));

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        if (dictionary != null) {
            System.out.println("\nTest translations:");
            System.out.println("'cat' in Indonesian: " + dictionary.translateEnglishToIndonesian("cat"));
            System.out.println("'buku' in English: " + dictionary.translateIndonesianToEnglish("buku"));
        }
    }
}
