package org.txazo.cache.through;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * 消除缓存穿透的MemcachedClient
 */
public class ThroughMemcachedClient extends MemcachedClient {

    // 空缓存的失效时间30s
    private static final int EMPTY_EXPIRE_TIME = 30;

    public ThroughMemcachedClient(InetSocketAddress... ia) throws IOException {
        super(ia);
    }

    @Override
    public OperationFuture<Boolean> set(String key, int exp, Object o) {
        if (o == null) {
            return super.set(key, EMPTY_EXPIRE_TIME, CacheEntity.EMPTY);
        } else {
            return super.set(key, exp, o);
        }
    }

    @Override
    public CacheResponse get(String key) {
        Object obj = super.get(key);
        if (obj == null) {
            return CacheResponse.RESPONSE_MISS;
        } else if (obj instanceof CacheEntity) {
            return CacheResponse.RESPONSE_EMPTY;
        }
        return new CacheResponse(CacheStatus.OK, obj);
    }

}
