package com.coffeecode.config;

public class DictionaryConfig {

    private DictionaryConfig() {
        throw new IllegalStateException("Utility class");
    }

    private static final String ENGLISH_PATH = "src/main/resources/dictionary/english.json";
    private static final String INDONESIAN_PATH = "src/main/resources/dictionary/indonesian.json";

    public static String getEnglishPath() {
        return ENGLISH_PATH;
    }

    public static String getIndonesianPath() {
        return INDONESIAN_PATH;
    }
}
