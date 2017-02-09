package com.ApiUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.lang3.StringUtils;


import org.json.JSONObject;

public class JsonUtil {



    public static <T> T readJson(String filePath, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(fixture(filePath), clazz);
    }

    public static <T> T parseJson(String json, Class<T> clazz) throws IOException {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return new ObjectMapper().readValue(json, clazz);
    }

    public static <T> T parseJson(JSONObject json, Class<T> clazz) throws IOException {
        if (json == null) {
            return null;
        }
        return new ObjectMapper().readValue(json.toString(), clazz);
    }

    public static <T> T parseJson(String json, Class<T> clazz, Class<?> jsonViewclazz) throws IOException {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return new ObjectMapper().readerWithView(jsonViewclazz).forType(clazz).readValue(json);
    }

    public static String toJson(Object arg) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(arg);
    }

    public static String toJsonAsString(Object arg) throws JsonProcessingException {
        if(arg == null) {
            return null;
        }
        return new ObjectMapper()
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
                .writeValueAsString(arg).replaceAll("\\\\", "");
    }

    public static String toJsonAsString(Object arg, Class<?> jsonViewClazz) throws JsonProcessingException {
        return new ObjectMapper()
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
                .writerWithView(jsonViewClazz)
                .writeValueAsString(arg).replaceAll("\\\\", "");
    }

    private static InputStream fixture(String fixture) {
        return JsonUtil.class.getResourceAsStream(fixture);
    }

//    public static String stringToPretyJsonString(String jsonString) {
//        try {
//            JSONObject json = new JSONObject(jsonString);
//            return json.toString(4);
//        } catch (JSONException ex) {
//
//           sout
//        }
//    }

    public static String toJsonStringHandledExceptions(Object obj) {
        String jsonString = "";
        try {
            jsonString = toJsonAsString(obj);
        } catch (Exception ex) {

        }
        return jsonString;
    }
}
