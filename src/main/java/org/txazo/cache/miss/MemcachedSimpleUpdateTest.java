package org.txazo.cache.miss;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.Callable;

public class MemcachedSimpleUpdateTest extends MemcachedTest {

    @Test
    public void test() throws IOException, InterruptedException {
        final String key = "key";

        concurrentGet(1000, 0, new Callable() {

            @Override
            public Object call() throws Exception {
                return get(key);
            }

        });

        Thread.sleep(100000);
    }

    private Object get(String key) {
        Object value = memcachedClient.get(key);
        if (value == null) {
            value = getFromDB(key);
            memcachedClient.set(key, 3, value);
            logger.info("cache miss");
        } else {
            logger.info("cache hit");
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
