package org.txazo.tool.util;

import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * MapUtils
 */
public abstract class MapUtils {

    public static Map<Object, Object> buildMap(Object... keyValuePairs) {
        Map<Object, Object> map = new HashMap<>();
        if (ArrayUtils.isNotEmpty(keyValuePairs)) {
            for (int i = 0; i < keyValuePairs.length; i += 2) {
                map.put(keyValuePairs[i], i == keyValuePairs.length - 1 ? null : keyValuePairs[i + 1]);
            }
        }
        return map;
    }

}
