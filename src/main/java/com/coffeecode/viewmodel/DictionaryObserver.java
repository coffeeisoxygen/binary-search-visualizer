package com.coffeecode.viewmodel;

public interface DictionaryObserver {
    void onTranslationComplete(String result);

    void onError(String errorMessage);
}