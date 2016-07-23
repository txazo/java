package org.txazo.cache.miss.prefill;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

public class PrefillMemcachedProxy extends MemcachedClient {

    private static final int PREFILL_SECOND = 5;

    private ConcurrentHashMap<String, PrefillCallback<?>> prefillCallbackMap = new ConcurrentHashMap<String, PrefillCallback<?>>();

    public PrefillMemcachedProxy(InetSocketAddress... ia) throws IOException {
        super(ia);
    }

    public OperationFuture<Boolean> set(String key, int exp, Object o, PrefillCallback<?> prefillCallback) {
        prefillCallbackMap.put(key, prefillCallback);
        return super.set(key, exp, new CacheValue(exp, o));
    }

    @Override
    public Object get(String key) {
        Object value = super.get(key);
        if (!(value instanceof CacheValue)) {
            return value;
        }

        CacheValue cacheValue = (CacheValue) value;
        if (cacheValue == null) {
            return cacheValue;
        }

        // 获取分布式锁
        prefillCache(key, cacheValue);
        return cacheValue.getValue();
    }

    /**
     * 预填充
     */
    private void prefillCache(String key, CacheValue cacheValue) {
        long currentTime = System.currentTimeMillis() / 1000;
        if (cacheValue.getEndTime() >= currentTime - PREFILL_SECOND) {
            PrefillCallback prefillCallback = prefillCallbackMap.get(key);
            if (prefillCallback != null) {
                this.set(key, cacheValue.getExpireTime(), prefillCallback.prefill(), prefillCallback);
            }
        }
    }

}
