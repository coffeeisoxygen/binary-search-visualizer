/**
 * Implementation of {@link IDictionaryLoader} that loads dictionary data from JSON files.
 * Uses Jackson library for JSON parsing.
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
     * Constructs a new JsonLoader.
     * Initializes the JSON object mapper for parsing dictionary files.
     */
    public JsonLoader() {
        this.mapper = new ObjectMapper();
    }

    /**
     * Loads dictionary data from a JSON file specified in the
     * {@link DictionaryConfig}.
     * 
     * @return An {@link IDictionaryData} object containing the loaded dictionary
     *         entries
     * @throws IOException if there is an error reading or parsing the JSON file
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
            String errorMessage = "Failed to load dictionary from path: " + DictionaryConfig.getDictionaryPath();
            LOGGER.error(errorMessage, e);
            throw new DictionaryException(errorMessage, e);
        } catch (Exception e) {
            String errorMessage = "Unexpected error occurred while loading dictionary";
            LOGGER.error(errorMessage, e);
            throw new DictionaryException(errorMessage, e);
        }
    }
}