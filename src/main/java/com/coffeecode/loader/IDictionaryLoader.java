package com.coffeecode.loader;

import java.io.IOException;

import com.coffeecode.model.IDictionaryData;

public interface IDictionaryLoader {
    IDictionaryData loadDictionaries() throws IOException;
}