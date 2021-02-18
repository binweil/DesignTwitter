package com.twitter.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class AppConfig {
    private static Map<String, Map<String, Object>> configuration;

    static {
        try {
            Resource resource = new ClassPathResource("configuration.json");
            configuration = new ObjectMapper().readValue(resource.getFile(), Map.class);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public AppConfig() {
    }

    public static Object getParameter(String stage, String key) {
        Map<String, Object> mapping = configuration.get(stage);
        Object value = mapping.get(key);
        return value;
    }
}
