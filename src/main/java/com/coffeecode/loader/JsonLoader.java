package com.coffeecode.loader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.coffeecode.exception.CustomException;
import com.coffeecode.model.DictionaryData;
import com.coffeecode.model.IDictionaryData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonLoader implements IDictionaryLoader {
    private final ObjectMapper mapper;
    private final String englishPath;
    private final String indonesianPath;

    public JsonLoader() {
        this.mapper = new ObjectMapper();
        this.englishPath = "src/main/resources/dictionary/english.json";
        this.indonesianPath = "src/main/resources/dictionary/indonesian.json";
    }

    @Override
    public IDictionaryData loadDictionaries() {
        IDictionaryData dictionary = new DictionaryData();

        try {
            JsonNode englishJson = mapper.readTree(new File(englishPath));
            JsonNode indonesianJson = mapper.readTree(new File(indonesianPath));

            List<Map<String, String>> englishData = mapper.convertValue(
                    englishJson.get("data"),
                    new TypeReference<List<Map<String, String>>>() {
                    });

            List<Map<String, String>> indonesianData = mapper.convertValue(
                    indonesianJson.get("data"),
                    new TypeReference<List<Map<String, String>>>() {
                    });

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