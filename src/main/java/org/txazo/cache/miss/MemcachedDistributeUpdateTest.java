package org.txazo.cache.miss;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class MemcachedDistributeUpdateTest extends MemcachedTest {

    private ExtendMemcachedClient memcachedClient = null;

    private static final int COUNT = 10000;
    private static AtomicInteger atomicCount1 = new AtomicInteger(0);
    private static AtomicInteger atomicCount2 = new AtomicInteger(0);
    private static AtomicInteger atomicCount = new AtomicInteger(0);

    @Before
    public void before() throws IOException {
        memcachedClient = new ExtendDistributedMemcachedClient(new InetSocketAddress("121.42.178.174", 9998));
    }

    @Test
    public void test() throws IOException, InterruptedException {
        final String key = "key";

        concurrentGet(COUNT, 3, new Callable() {

            @Override
            public Object call() throws Exception {
                try {
                    get(key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

        });

        Thread.sleep(1000000);
    }

    private Object get(final String key) {
        int i = atomicCount2.incrementAndGet();
        logger.info("get enter2 " + i);
        Object value = memcachedClient.get(key);
        if (value == null) {
            logger.info("cache miss");
            value = memcachedClient.getAndSet(i, key, new Callable() {

                @Override
                public Object call() throws Exception {
                    return getFromDB(key);
                }

            });
        } else {
            logger.info("cache hit");
        }
        logger.info("get enter " + atomicCount1.incrementAndGet());
        if ("value".equals(value)) {
            logger.info("get " + atomicCount.incrementAndGet());
        } else {
            logger.info("get error value " + value + " " + atomicCount.incrementAndGet());
        }
        return value;
    }

    private Object getFromDB(String key) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "value";
    }

}
