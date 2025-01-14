package com.coffeecode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeecode.loader.JsonLoader;
import com.coffeecode.model.DictionaryData;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        try {
            DictionaryData dictionary = JsonLoader.loadDictionaries();
            
            System.out.println("English to Indonesian:");
            dictionary.getEnglishToIndonesian().forEach((k, v) -> 
                System.out.println(k + " -> " + v));
            
            System.out.println("\nIndonesian to English:");
            dictionary.getIndonesianToEnglish().forEach((k, v) -> 
                System.out.println(k + " -> " + v));
                
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
