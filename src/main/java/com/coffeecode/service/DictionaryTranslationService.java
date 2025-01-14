package com.coffeecode.service;

import com.coffeecode.model.IDictionaryData;

public class DictionaryTranslationService implements TranslationService {
    private final IDictionaryData dictionary;

    public DictionaryTranslationService(IDictionaryData dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public String translateToIndonesian(String english) {
        return dictionary.translateEnglishToIndonesian(english);
    }

    @Override
    public String translateToEnglish(String indonesian) {
        return dictionary.translateIndonesianToEnglish(indonesian);
    }

    @Override
    public void displayAllTranslations() {
        System.out.println("English to Indonesian:");
        dictionary.getEnglishToIndonesian()
                .forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}