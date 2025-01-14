/**
 * The `JsonLoader` class implements the `IDictionaryLoader` interface to load dictionary data from a
 * JSON file using Jackson library in Java.
 */
package com.coffeecode.loader;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeecode.config.DictionaryConfig;
import com.coffeecode.exception.DictionaryException;
import com.coffeecode.model.DictionaryData;
import com.coffeecode.model.IDictionaryData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonLoader implements IDictionaryLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonLoader.class);
    private final ObjectMapper mapper;

    /**
     * Constructs a new JsonLoader with an ObjectMapper instance.
     */
    public JsonLoader() {
        this.mapper = new ObjectMapper();
    }

    /**
     * Loads dictionary data from a JSON file specified in the DictionaryConfig.
     *
     * @return an IDictionaryData instance containing the loaded dictionary data.
     * @throws IOException if an I/O error occurs while reading the JSON file.
     */
    @Override
    public IDictionaryData loadDictionaries() throws IOException {
        DictionaryData.Builder builder = DictionaryData.builder();

        try {
            LOGGER.info("Loading dictionary from path: {}", DictionaryConfig.getDictionaryPath());
            JsonNode dictionaryData = mapper.readTree(new File(DictionaryConfig.getDictionaryPath()))
                    .get("data");

            for (JsonNode entry : dictionaryData) {
                builder.addEntry(
                        entry.get("english").asText(),
                        entry.get("indonesian").asText());
            }

            LOGGER.info("Dictionary loaded successfully");
            return builder.build();
        } catch (IOException e) {
            LOGGER.error("Failed to load dictionary", e);
            throw new DictionaryException("Failed to load dictionary", e);
        }
    }
}