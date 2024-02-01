package com.fanty.core.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>Json正反序列化工具类</p>
 *
 * @author zhaosh
 * @date 2024/02/01
 */
public class JsonUtils {

    /** 映射 */
    public static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    }

    /**
     * json 实用程序
     */
    public JsonUtils() {
    }

    /**
     * 对象转 JSON
     *
     * @param data 数据
     * @return {@link String}
     */
    public static String objectToJson(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    /**
     * 对象到 JSON 类型
     *
     * @param data          数据
     * @param typeReference 类型引用
     * @return {@link String}
     */
    public static String objectToJsonWithType(Object data, TypeReference typeReference) {
        try {
            return MAPPER.writerFor(typeReference).writeValueAsString(data);
        } catch (JsonProcessingException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    /**
     * JSON 为 POJO
     *
     * @param jsonData JSON 数据
     * @param beanType 豆类类型
     * @return {@link T}
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    /**
     * JSON 列出
     *
     * @param jsonData JSON 数据
     * @param beanType 豆类类型
     * @return {@link List}<{@link T}>
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, new Class[]{beanType});

        try {
            return (List) MAPPER.readValue(jsonData, javaType);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    /**
     * 解析地图
     *
     * @param jsonStr json str
     * @return {@link Map}<{@link String}, {@link Object}>
     * @throws IOException ioexception
     */
    public static Map<String, Object> parseMap(String jsonStr) throws IOException {
        return (Map) MAPPER.readValue(jsonStr, Map.class);
    }

    /**
     * 解析列表
     *
     * @param jsonStr json str
     * @return {@link List}<{@link String}>
     * @throws IOException ioexception
     */
    public static List<String> parseList(String jsonStr) throws IOException {
        return (List) MAPPER.readValue(jsonStr, new TypeReference<List<String>>() {
        });
    }
}