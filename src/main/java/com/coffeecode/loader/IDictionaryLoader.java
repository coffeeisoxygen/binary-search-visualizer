package com.coffeecode.loader;

import java.io.IOException;

import com.coffeecode.model.IDictionaryData;

/**
 * Interface for dictionary data loading operations.
 * Defines the contract for loading dictionary data from various sources.
 */
public interface IDictionaryLoader {
    /**
     * Loads dictionary data from a configured source.
     *
     * @return an {@link IDictionaryData} object containing the loaded entries
     * @throws IOException if there is an error reading the dictionary data
     */
    IDictionaryData loadDictionaries() throws IOException;
}