package org.txazo.tool.api;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public abstract class ResponseUtils {

    public static String json(int code) {
        return json(code, null, null);
    }

    public static String json(int code, String message) {
        return json(code, message, null);
    }

    public static String json(int code, Map<String, Object> data) {
        return json(code, null, data);
    }

    public static String json(int code, String message, Map<String, Object> data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("message", message);
        map.put("data", data);
        return JSON.toJSONString(map);
    }

}
