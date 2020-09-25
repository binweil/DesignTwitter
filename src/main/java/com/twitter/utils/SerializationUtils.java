package com.twitter.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class SerializationUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public SerializationUtils() {};

    public static String serialize(Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }

    public static <T> T deSerialize (String jsonString, Class<T> objectClass) throws IOException {
        return mapper.readValue(jsonString, mapper.constructType(objectClass));
    }

    public static String serializeWithDateFormat(Object object, String dateFormat) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        mapper.setDateFormat(simpleDateFormat);
        return mapper.writeValueAsString(object);
    }

    public static Map<String, Object> readAsMap(String jsonString) throws IOException {
        return (Map)deSerialize(jsonString, Map.class);
    }

    public static ObjectMapper getJsonMapper() {return mapper;}
}
