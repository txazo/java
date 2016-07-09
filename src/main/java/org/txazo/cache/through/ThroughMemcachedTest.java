package org.txazo.cache.through;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ThroughMemcachedTest {

    private ThroughMemcachedClient memcachedClient;

    @Before
    public void before() throws IOException {
        memcachedClient = new ThroughMemcachedClient(new InetSocketAddress("121.42.178.174", 9998));
    }

    @Test
    public void test() {
        String key = "key";
        Assert.assertTrue(memcachedClient.get(key).isMiss());

        memcachedClient.set("key", 2, null);
        Assert.assertTrue(memcachedClient.get(key).isEmpty());

        memcachedClient.set("key", 2, "value");
        CacheResponse response = memcachedClient.get(key);
        Assert.assertTrue(response.isOk());
        Assert.assertEquals("value", response.getValue());
    }

}
