package com.coffeecode.viewmodel;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeecode.loader.IDictionaryLoader;
import com.coffeecode.loader.JsonLoader;
import com.coffeecode.model.IDictionaryData;

public class DictionaryViewModel {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryViewModel.class);
    private final IDictionaryLoader loader;
    private IDictionaryData dictionary;
    private final ExecutorService executor;

    public DictionaryViewModel() {
        this.loader = new JsonLoader();
        this.executor = Executors.newSingleThreadExecutor();
    }

    public void initialize() {
        try {
            dictionary = loader.loadDictionaries();
            LOGGER.info("Dictionary initialized successfully");
        } catch (IOException e) {
            LOGGER.error("Failed to initialize dictionary", e);
        }
    }

    public String translate(String word) {
        if (dictionary == null) {
            LOGGER.warn("Dictionary not initialized");
            return "";
        }
        return dictionary.translate(word);
    }

    public Map<String, String> getAllEntries() {
        if (dictionary == null) {
            LOGGER.warn("Dictionary not initialized");
            return Map.of();
        }
        return dictionary.getDictionary();
    }

    public void shutdown() {
        executor.shutdown();
        LOGGER.info("ViewModel shutdown complete");
    }
}