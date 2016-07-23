package org.txazo.cache.miss.prefill;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Date;

public class PrefillMemcachedProxyTest {

    private PrefillMemcachedProxy memcachedProxy;

    @Before
    public void before() throws IOException {
        memcachedProxy = new PrefillMemcachedProxy(new InetSocketAddress("127.0.0.1", 9000));
    }

    @Test
    public void testCachePrefill() throws InterruptedException {
        String key = "nextStartTime";
        memcachedProxy.set(key, 5, getNextStartTime(), new PrefillCallback<String>() {

            @Override
            public String prefill() {
                return getNextStartTime();
            }

        });

        for (int i = 0; i < 10; i++) {
            System.out.println("Cache Value: " + memcachedProxy.get(key));
            Thread.sleep(3000);
        }
    }

    private static String getNextStartTime() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

}
