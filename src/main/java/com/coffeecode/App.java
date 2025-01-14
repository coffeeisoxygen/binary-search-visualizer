package com.coffeecode;

import java.io.IOException;

import com.coffeecode.loader.IDictionaryLoader;
import com.coffeecode.loader.JsonLoader;
import com.coffeecode.model.IDictionaryData;

public class App {
    public static void main(String[] args) {
        try {
            IDictionaryLoader loader = new JsonLoader();
            IDictionaryData dictionary = loader.loadDictionaries();

            // Display translations
            System.out.println("Dictionary entries:");
            dictionary.getDictionary()
                    .forEach((k, v) -> System.out.println(k + " -> " + v));

            // Test translations
            System.out.println("\nTest translations:");
            System.out.println("'cat' translation: " + dictionary.translate("cat"));
            System.out.println("'buku' translation: " + dictionary.translate("buku"));

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}