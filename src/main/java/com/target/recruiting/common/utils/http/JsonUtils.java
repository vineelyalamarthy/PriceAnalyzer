package com.target.recruiting.common.utils.http;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static String toJson(final Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException converting to object json: {}", obj.getClass().getSimpleName(), e);
            throw new IllegalArgumentException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error("IOException converting fromJson json: {}", json);
            throw new IllegalArgumentException(e);
        }

    }

    public static <T> T fromJson(String input, TypeReference<T> typeReference) throws IOException {
        try {
            return objectMapper.readValue(input, typeReference);
        } catch (JsonParseException e) {
            logger.error("JsonParseException loading json: {}", input);
            throw new IllegalArgumentException(e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException loading json: {}", input);
            throw new IllegalArgumentException(e);
        }

    }
}