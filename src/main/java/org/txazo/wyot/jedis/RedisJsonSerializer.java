package org.txazo.wyot.jedis;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

public class RedisJsonSerializer {

    protected <V> String serialize(V v) {
        if (v instanceof String) {
            return (String) v;
        }
        return JSON.toJSONString(v);
    }

    protected <V> V deserialize(String value, Class<V> classType) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        if (classType == String.class) {
            return (V) value;
        }
        return JSON.parseObject(value, classType);
    }

}
