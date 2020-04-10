package org.txazo.wyot.jedis;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RedisLambdaDomain {

    // 默认失效时间10分钟
    private static final long DEFAULT_EXPIRE_SECONDS = 10 * 60L;

    @Autowired
    private RedisClient redisClient;

    private static <K> String buildKey(K k) {
        return k.toString();
    }

    private static <K> String buildKey(String prefix, K k) {
        return prefix + k.toString();
    }

    private static <K> List<String> buildKeys(String prefix, List<K> keys) {
        return keys.stream().map(k -> buildKey(prefix, k)).collect(Collectors.toList());
    }

    private static <K> String[] buildKeyArray(String prefix, List<K> keys) {
        return keys.stream().map(k -> buildKey(prefix, k)).collect(Collectors.toList()).toArray(new String[]{});
    }

    public <K, V> V getDataFromCacheWithDB(String prefix, K k, Class<V> valueType, Function<K, V> dbFunction) {
        return getDataFromCacheWithDB(prefix, k, valueType, dbFunction, DEFAULT_EXPIRE_SECONDS);
    }

    public <K, V> V getDataFromCacheWithDB(String prefix, K k, Class<V> valueType, Function<K, V> dbFunction, long expireSeconds) {
        String key = buildKey(prefix, k);
        V result = redisClient.get(key, valueType);
        if (result == null) {
            result = dbFunction.apply(k);
            if (result != null) {
                redisClient.set(key, serialize(result));
                if (expireSeconds != -1) {
                    redisClient.expire(key, (int) expireSeconds);
                }
            }
        }
        return result;
    }

    public <K, V> List<V> mgetDataListFromCacheWithDB(String prefix, List<K> keys, Class<V> valueType, Function<List<K>, List<V>> dbFunction, Function<V, K> keyFunction) {
        return mgetDataListFromCacheWithDB(prefix, keys, valueType, dbFunction, keyFunction, DEFAULT_EXPIRE_SECONDS);
    }

    public <K, V> List<V> mgetDataListFromCacheWithDB(String prefix, List<K> keys, Class<V> valueType, Function<List<K>, List<V>> dbFunction, Function<V, K> keyFunction, long expireTime) {
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.EMPTY_LIST;
        }
        Map<K, V> dataMap = mgetDataFromCacheWithDB(prefix, keys, valueType, dbFunction, keyFunction, expireTime);
        return keys.stream().map(k -> dataMap.get(k)).filter(e -> e != null).collect(Collectors.toList());
    }

    public <K, V> Map<K, V> mgetDataFromCacheWithDB(String prefix, List<K> keys, Class<V> valueType, Function<List<K>, List<V>> dbFunction, Function<V, K> keyFunction) {
        return mgetDataFromCacheWithDB(prefix, keys, valueType, dbFunction, keyFunction, DEFAULT_EXPIRE_SECONDS);
    }

    public <K, V> Map<K, V> mgetDataFromCacheWithDB(String prefix, List<K> keys, Class<V> valueType, Function<List<K>, List<V>> dbFunction, Function<V, K> keyFunction, long expireTime) {
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.EMPTY_MAP;
        }

        Map<K, V> dataMap = new HashMap<>();
        List<String> values = redisClient.mget(buildKeyArray(prefix, keys));
        if (CollectionUtils.isNotEmpty(values)) {
            for (int i = 0; i < keys.size(); i++) {
                dataMap.put(keys.get(i), values.size() > i ? deserialize(values.get(i), valueType) : null);
            }
            List<K> missKeys = dataMap.entrySet().stream().filter(e -> e.getValue() == null).map(Map.Entry::getKey).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(missKeys)) {
                try {
                    List<V> missValues = dbFunction.apply(missKeys);
                    if (CollectionUtils.isNotEmpty(missValues)) {
                        Map<K, V> missDataMap = missValues.stream().collect(Collectors.toMap(v -> keyFunction.apply(v), v -> v));
                        dataMap.putAll(missDataMap);
                        redisClient.mset(buildKeyValuePairs(prefix, missDataMap));
                    }
                } catch (Exception e) {
                    throw new RedisException(e);
                }
            }
        }
        return dataMap;
    }

    public <K, V> List<V> hmgetDataListFromCacheWithDB(String hashKey, String prefix, List<K> keys, Class<V> valueType, Function<List<K>, List<V>> dbFunction, Function<V, K> keyFunction) {
        return hmgetDataListFromCacheWithDB(hashKey, prefix, keys, valueType, dbFunction, keyFunction, DEFAULT_EXPIRE_SECONDS);
    }

    public <K, V> List<V> hmgetDataListFromCacheWithDB(String hashKey, String prefix, List<K> keys, Class<V> valueType, Function<List<K>, List<V>> dbFunction, Function<V, K> keyFunction, long expireTime) {
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.EMPTY_LIST;
        }
        Map<K, V> dataMap = hmgetDataFromCacheWithDB(hashKey, prefix, keys, valueType, dbFunction, keyFunction, expireTime);
        return keys.stream().map(k -> dataMap.get(k)).filter(e -> e != null).collect(Collectors.toList());
    }

    public <K, V> Map<K, V> hmgetDataFromCacheWithDB(String hashKey, String prefix, List<K> keys, Class<V> valueType, Function<List<K>, List<V>> dbFunction, Function<V, K> keyFunction) {
        return hmgetDataFromCacheWithDB(hashKey, prefix, keys, valueType, dbFunction, keyFunction, DEFAULT_EXPIRE_SECONDS);
    }

    public <K, V> Map<K, V> hmgetDataFromCacheWithDB(String hashKey, String prefix, List<K> keys, Class<V> valueType, Function<List<K>, List<V>> dbFunction, Function<V, K> keyFunction, long expireTime) {
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.EMPTY_MAP;
        }

        Map<K, V> dataMap = new HashMap<>();
        List<V> values = redisClient.hmget(hashKey, buildKeys(prefix, keys), valueType);
        if (CollectionUtils.isNotEmpty(values)) {
            for (int i = 0; i < keys.size(); i++) {
                dataMap.put(keys.get(i), values.size() > i ? values.get(i) : null);
            }
            List<K> missKeys = dataMap.entrySet().stream().filter(e -> e.getValue() == null).map(Map.Entry::getKey).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(missKeys)) {
                try {
                    List<V> missValues = dbFunction.apply(missKeys);
                    if (CollectionUtils.isNotEmpty(missValues)) {
                        Map<K, V> missDataMap = missValues.stream().collect(Collectors.toMap(v -> keyFunction.apply(v), v -> v));
                        dataMap.putAll(missDataMap);
                        redisClient.hmset(hashKey, missDataMap.entrySet().stream()
                                .collect(Collectors.toMap(e -> buildKey(prefix, e.getKey()), e -> serialize(e.getValue()))));
                    }
                } catch (Exception e) {
                    throw new RedisException(e);
                }
            }
        }
        return dataMap;
    }

    public <K> void delete(String prefix, K k) {
        redisClient.del(buildKey(prefix, k));
    }

    public <K> int getCount(String prefix, K k, Function<K, Integer> dbFunction) {
        String key = buildKey(prefix, k);
        String value = redisClient.get(key);
        Integer count = NumberUtils.toInt(value, -1);
        if (count == -1) {
            count = dbFunction.apply(k);
            if (count != null) {
                redisClient.setnx(key, String.valueOf(count));
                redisClient.expire(key, -1);
            }
        }
        return count;
    }

    public <K> void increse(String prefix, K k, Function<K, Integer> dbFunction) {
        String key = buildKey(prefix, k);
        if (!redisClient.exists(key)) {
            Integer count = dbFunction.apply(k);
            if (count != null) {
                redisClient.setnx(key, String.valueOf(count));
                redisClient.expire(key, -1);
            }
        }
        redisClient.incr(key);
    }

    public <K> void hincr(String hashKey, K k, Function<K, Integer> dbFunction) {
        Long ret = eval(RedisScripts.SCRIPT_HINCR, Collections.singletonList(hashKey), Collections.singletonList(buildKey(k)));
        if (ret == 0) {
            Integer count = dbFunction.apply(k);
            if (count != null) {
                eval(RedisScripts.SCRIPT_HINCR2, Collections.singletonList(hashKey), Arrays.asList(buildKey(k), String.valueOf(count + 1)));
            }
        }
    }

    public <K> String hget(String hashKey, K k) {
        return redisClient.hget(hashKey, buildKey(k));
    }

    public <K> String hgetIfAbsent(String hashKey, K k, Function<K, String> dbFunction) {
        String value = redisClient.hget(hashKey, buildKey(k));
        if (value != null) {
            return value;
        }

        String v = dbFunction.apply(k);
        if (v != null) {
            redisClient.hsetnx(hashKey, buildKey(k), v);
            return v;
        }

        return null;
    }

    public Map<String, String> hmget(String hashKey, List<String> keys) {
        List<String> values = redisClient.hmget(hashKey, keys.toArray(new String[]{}));
        if (CollectionUtils.isEmpty(values)) {
            return Collections.EMPTY_MAP;
        }

        Map<String, String> data = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            data.put(keys.get(i), values.size() > i && values.get(i) != null ? values.get(i) : null);
        }
        return data;
    }

    public <V> void hscan(String hashKey, int batchRedisSize, int batchConsumeSize, Consumer<List<Map.Entry<String, String>>> consumer) throws Exception {
        String cursor = "0";
        ScanParams scanParams = new ScanParams();
        scanParams.count(batchRedisSize);
        scanParams.match("*");

        List<Map.Entry<String, String>> result = null;
        while (true) {
            ScanResult<Map.Entry<String, String>> scanResult = redisClient.hscan(hashKey, cursor, scanParams);
            cursor = scanResult.getStringCursor();
            result = scanResult.getResult();
            if (CollectionUtils.isNotEmpty(result)) {
                int startIndex = 0;
                while (startIndex + batchConsumeSize <= result.size()) {
                    consumer.accept(scanResult.getResult().subList(startIndex, startIndex + batchConsumeSize));
                    startIndex += batchConsumeSize;
                }
                if (startIndex < result.size()) {
                    consumer.accept(scanResult.getResult().subList(startIndex, result.size()));
                }
            }

            if ("0".equals(cursor)) {
                break;
            }
        }
    }

    public <T> T eval(final String script, final List<String> keys, final List<String> values) {
        return (T) redisClient.eval(script, keys, values);

//        return (T) redisClusterClient.getRedisTemplate().execute(new RedisCallback<Object>() {
//
//            @Override
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                if (connection.getNativeConnection() instanceof Jedis) {
//                    return ((Jedis) connection.getNativeConnection()).eval(script, keys, values);
//                } else if (connection.getNativeConnection() instanceof JedisCluster) {
//                    return ((JedisCluster) connection.getNativeConnection()).eval(script, keys, values);
//                }
//                return null;
//            }
//
//        });
    }

    private static <V> String serialize(V v) {
        if (v instanceof String) {
            return (String) v;
        }
        return JSON.toJSONString(v);
    }

    private static <V> V deserialize(String value, Class<V> classType) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        if (classType == String.class) {
            return (V) value;
        }
        return JSON.parseObject(value, classType);
    }

    private static <K, V> String[] buildKeyValuePairs(String prefix, Map<K, V> map) {
        List<String> keyValues = new ArrayList<>();
        map.forEach((k, v) -> {
            keyValues.add(buildKey(prefix, k));
            keyValues.add(serialize(v));
        });
        return keyValues.toArray(new String[]{});
    }

    public List<String> mget(List<String> keys) {
        return redisClient.mget(keys.toArray(new String[]{}));
    }

    public String mset(Map<String, String> map) {
        List<String> keyValueList = new ArrayList<>(map.size() * 2 + 1);
        map.forEach((k, v) -> {
            keyValueList.add(k);
            keyValueList.add(v);
        });
        return redisClient.mset(keyValueList.toArray(new String[]{}));
    }

}
