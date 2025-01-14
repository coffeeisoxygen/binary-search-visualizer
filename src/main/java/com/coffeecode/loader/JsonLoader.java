package com.coffeecode.loader;

import java.io.File;
import java.io.IOException;

import com.coffeecode.config.DictionaryConfig;
import com.coffeecode.exception.DictionaryException;
import com.coffeecode.model.DictionaryData;
import com.coffeecode.model.IDictionaryData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonLoader implements IDictionaryLoader {
    private final ObjectMapper mapper;

    public JsonLoader() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public IDictionaryData loadDictionaries() throws IOException {
        DictionaryData.Builder builder = DictionaryData.builder();

        try {
            JsonNode dictionaryData = mapper.readTree(new File(DictionaryConfig.getDictionaryPath()))
                    .get("data");

            for (JsonNode entry : dictionaryData) {
                builder.addEntry(
                        entry.get("english").asText(),
                        entry.get("indonesian").asText());
            }

            return builder.build();
        } catch (IOException e) {
            throw new DictionaryException("Failed to load dictionary", e);
        }
    }
}