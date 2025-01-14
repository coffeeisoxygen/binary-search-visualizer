package com.coffeecode;

import java.io.IOException;

import com.coffeecode.loader.IDictionaryLoader;
import com.coffeecode.loader.JsonLoader;
import com.coffeecode.model.IDictionaryData;

/**
 * Main application class for the Dictionary application.
 * This class provides the entry point for the dictionary translation program
 * which loads dictionary data from JSON files and performs translations between
 * English and Indonesian languages.
 */
public class App {
    /**
     * Private constructor to prevent instantiation.
     * This is a utility class with only static methods.
     */
    private App() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * The main entry point of the application.
     * Loads the dictionary data and demonstrates basic translation functionality.
     * 
     * <p>
     * This method:
     * <ul>
     * <li>Initializes a dictionary loader</li>
     * <li>Loads dictionary data from JSON files</li>
     * <li>Displays all dictionary entries</li>
     * <li>Demonstrates translation in both directions (English to Indonesian and
     * vice versa)</li>
     * </ul>
     * 
     * @param args command line arguments (not used)
     */
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