package org.txazo.wyot.jedis;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

abstract class RedisEntityClient extends MultiKeyRedisClient {

    public <V> String set(String key, V v) {
        return set(key, serialize(v));
    }

    public <V> String set(String key, V v, String nxxx, String expx, long time) {
        return set(key, serialize(v), nxxx, expx, time);
    }

    public <V> String set(String key, V v, String nxxx) {
        return set(key, serialize(v), nxxx);
    }

    public <V> V get(String key, Class<V> classType) {
        return deserialize(get(key), classType);
    }

    public <V> Long hset(String key, String field, V v) {
        return hset(key, field, serialize(v));
    }

    public <V> Long hsetnx(String key, String field, V v) {
        return hsetnx(key, field, serialize(v));
    }

    public <V> V hget(String key, String field, Class<V> classType) {
        return deserialize(hget(key, field), classType);
    }

    public <V> Map<String, V> hgetAll(String key, Class<V> classType) {
        Map<String, String> data = hgetAll(key);
        if (data == null || data.size() < 1) {
            return Collections.EMPTY_MAP;
        }
        return data.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> deserialize(e.getValue(), classType)));
    }

    public <V> List<V> hmget(String key, List<String> fields, Class<V> classType) {
        List<String> values = hmget(key, fields.toArray(new String[]{}));
        if (values.get(0) == null || values.size() < 1) {
            return Collections.EMPTY_LIST;
        }
        return values.stream().map(v -> deserialize(v, classType)).collect(Collectors.toList());
    }

}
