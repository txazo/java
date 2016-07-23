package org.txazo.cache.miss;

import net.spy.memcached.internal.OperationFuture;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

public class ExtendDistributedMemcachedClient extends ExtendMemcachedClient {

    public ExtendDistributedMemcachedClient(InetSocketAddress... ia) throws IOException {
        super(ia);
    }

    @Override
    protected Object gainCacheUpdateValue(String key, Callable callable) throws Exception {
        String expireKey = "expire_" + key;
        OperationFuture<Boolean> future = add(expireKey, 1, true);
        if (future.get()) {
            return callable.call();
        }
        return getAndWait(key);
    }

    private Object getAndWait(String key) throws Exception {
        int i = 0;
        Object value = null;
        while (i++ < 20) {
            value = get(key);
            if (value != null) {
                return value;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        throw new Exception("getAndWait timeout");
    }

}
