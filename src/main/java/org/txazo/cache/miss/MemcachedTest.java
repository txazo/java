package org.txazo.cache.miss;

import net.spy.memcached.MemcachedClient;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MemcachedTest {

    protected MemcachedClient memcachedClient;
    protected Logger logger = LoggerFactory.getLogger(MemcachedTest.class);
    private ExecutorService threadPool = Executors.newFixedThreadPool(50);

    @Before
    public void before() throws IOException {
        memcachedClient = new MemcachedClient(new InetSocketAddress("121.42.178.174", 9998));
    }

    protected void concurrentGet(int count, int wait, Callable callable) {
        int i = 0;
        while (i++ < count) {
            get(callable);
            if (wait > 0) {
                try {
                    Thread.sleep(wait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void get(final Callable callable) {
        threadPool.submit(new Callable() {

            @Override
            public Object call() throws Exception {
                return callable.call();
            }

        });
    }

}
