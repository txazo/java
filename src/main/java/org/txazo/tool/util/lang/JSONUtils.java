package org.txazo.tool.util.lang;

import com.alibaba.fastjson.JSONArray;
import org.txazo.tool.util.MapUtils;

/**
 * JSONUtils
 */
public abstract class JSONUtils {

    public static String buildJSONString(Object... keyValuePairs) {
        return JSONArray.toJSONString(MapUtils.buildMap(keyValuePairs));
    }

}
