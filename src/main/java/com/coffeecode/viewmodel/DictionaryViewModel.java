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
    private DictionaryObserver observer;

    public DictionaryViewModel() {
        this.loader = new JsonLoader();
        this.executor = Executors.newSingleThreadExecutor();
    }

    public void setObserver(DictionaryObserver observer) {
        this.observer = observer;
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
            if (observer != null) {
                observer.onError("Dictionary not initialized");
            }
            return "";
        }
        String result = dictionary.translate(word);
        if (result == null || result.isEmpty()) {
            if (observer != null) {
                observer.onError("Translation not found for word: " + word);
            }
        } else {
            if (observer != null) {
                observer.onTranslationComplete(result);
            }
        }
        return result;
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