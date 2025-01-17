package org.example.webmodule.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperConfig {
    public static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // Регистрируем модуль JavaTimeModule
        mapper.registerModule(new JavaTimeModule());

        // Отключаем WRITE_DATES_AS_TIMESTAMPS, если хотите использовать форматированные даты
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }
}