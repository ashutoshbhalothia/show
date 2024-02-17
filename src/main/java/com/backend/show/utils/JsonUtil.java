package com.backend.show.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String convertObjectToJsonString(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred while convertObjectToJsonString for data : {} ", data,ex);
        }
        return null;
    }

    public static <T> T convertJsonToObject(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred while convertJsonToObject ", ex);
        }
        return null;
    }

    public static <T> T convertObjectToType(Object object, Class<T> clazz) {
        try {
            return objectMapper.convertValue(object, clazz);
        } catch (Exception exp) {
            LOGGER.error("Exception occurred while convertMapToObject ", exp);
        }
        return null;
    }

    public static <T> T convertToObject(String json, TypeReference<T> typeReference) {
        T object = null;
        if(json == null)
            return null;
        try {
            object = objectMapper.readValue(json, typeReference);
        } catch (IOException ex) {
            LOGGER.error(MessageFormat.format("convert to object error", json, typeReference.toString()),ex);
        }
        return object;
    }

    //@TrackExecutionTime
    public <T> List<T> convertToOrderedCollection(Set<String> jsonSet, TypeReference<T> typeReference) {
        try {
            return jsonSet
                    .parallelStream()
                    .map(json -> convertToObject(json, typeReference))
                    .toList();
        }catch (Exception ex) {
            LOGGER.error(MessageFormat.format("convert To OrderedCollection error", typeReference.toString()),ex);
        }
        return null;
    }

}
