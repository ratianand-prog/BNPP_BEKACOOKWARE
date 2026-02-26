package utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Map;

public class LanguageManager {

    private static Map<String, Map<String, String>> labels;

    private LanguageManager() {
        // Prevent instantiation
    }

    public static void loadLanguage(String language) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream input = LanguageManager.class
                    .getClassLoader()
                    .getResourceAsStream("i18n/" + language + ".json");

            if (input == null) {
                throw new RuntimeException("Language file not found: " + language);
            }

            labels = mapper.readValue(
                    input,
                    new TypeReference<Map<String, Map<String, String>>>() {}
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to load language file", e);
        }
    }

    public static String getLabel(String page, String key) {

        if (labels == null) {
            throw new IllegalStateException("Language not loaded. Call loadLanguage() first.");
        }

        Map<String, String> pageMap = labels.get(page);

        if (pageMap == null) {
            throw new RuntimeException("Page not found in JSON: " + page);
        }

        String value = pageMap.get(key);

        if (value == null) {
            throw new RuntimeException("Key not found: " + key + " in page: " + page);
        }

        return value;
    }
}