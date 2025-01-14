package com.coffeecode.service;

public interface TranslationService {
    String translateToIndonesian(String english);

    String translateToEnglish(String indonesian);

    void displayAllTranslations();
}