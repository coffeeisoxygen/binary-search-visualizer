package com.coffeecode.loader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.coffeecode.exception.CustomException;
import com.coffeecode.model.DictionaryData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonLoader {
    private JsonLoader() {
        throw new IllegalStateException("Utility class");
    }

    public static DictionaryData loadDictionaries() {
        DictionaryData dictionary = new DictionaryData();
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Read JSON files
            JsonNode englishJson = mapper.readTree(new File("src/main/resources/dictionary/english.json"));
            JsonNode indonesianJson = mapper.readTree(new File("src/main/resources/dictionary/indonesian.json"));

            // Get data arrays
            List<Map<String, String>> englishData = mapper.convertValue(
                    englishJson.get("data"),
                    new TypeReference<List<Map<String, String>>>() {
                    });

            List<Map<String, String>> indonesianData = mapper.convertValue(
                    indonesianJson.get("data"),
                    new TypeReference<List<Map<String, String>>>() {
                    });

            // Populate dictionary
            englishData.forEach(entry -> dictionary.addEnglishToIndonesian(
                    entry.get("english"),
                    entry.get("indonesian")));

            indonesianData.forEach(entry -> dictionary.addIndonesianToEnglish(
                    entry.get("indonesian"),
                    entry.get("english")));

        } catch (JsonProcessingException e) {
            throw new CustomException("Failed to process JSON: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new CustomException("Failed to load dictionaries: " + e.getMessage(), e);
        }

        return dictionary;
    }
}