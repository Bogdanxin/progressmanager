package com.softlab.progressmanager.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gwx
 * @version 1.0
 * @program JsonUtils
 * @description json文件处理类
 * @date 2020/3/11 14:24
 */
public class JsonUtils {

    private final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将json文件处理成该泛型
     * @param json
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T getObjFromJson(String json, Class<T> valueType){
        T rtv = null;
        try {
            rtv = objectMapper.readValue(json, valueType);
        }catch (IOException ex){
            logger.error(ex.getLocalizedMessage());
        }
        return rtv;
    }

    /**
     * 将实体类转换为Json
     * @param object
     * @return
     */
    public static String getJsonFromObj(Object object) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(object);
        }catch (JsonProcessingException e){
            logger.error(e.getLocalizedMessage());
        }
        return json;
    }

    /**
     * 将传入的json文件转变为Map类
     * @param json
     * @return
     */
    public static Map<String, Object> getMapFromJson(String json){
        Map<String, Object> map = getObjFromJson(json, Map.class);

        if (map == null){
            return new HashMap<>(0);
        }
        return map;
    }
}
