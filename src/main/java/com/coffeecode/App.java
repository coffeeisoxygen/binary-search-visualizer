package com.coffeecode;

import com.coffeecode.loader.IDictionaryLoader;
import com.coffeecode.loader.JsonLoader;
import com.coffeecode.model.IDictionaryData;
import com.coffeecode.service.DictionaryTranslationService;
import com.coffeecode.service.TranslationService;

public class App {
    public static void main(String[] args) {
        try {
            IDictionaryLoader loader = new JsonLoader();
            IDictionaryData dictionary = loader.loadDictionaries();

            TranslationService translationService = new DictionaryTranslationService(dictionary);

            // Display translations
            translationService.displayAllTranslations();

            // Test specific translations
            System.out.println("\nTest translations:");
            System.out.println("'cat' in Indonesian: " +
                    translationService.translateToIndonesian("cat"));
            System.out.println("'buku' in English: " +
                    translationService.translateToEnglish("buku"));

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}