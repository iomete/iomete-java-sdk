package com.iomete.sdk.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JsonModel<T> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String toJson() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting to JSON", e);
        }
    }

    public T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting from JSON", e);
        }
    }
}
