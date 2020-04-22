package org.txazo.java.concurrency.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {

    /**
     * VM Args: -server -verbose:gc -Xms100M -Xmx100M
     */
    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            newThreadLocal();
        }
    }

    private void newThreadLocal() {
        ThreadLocal threadLocal = new ThreadLocal<byte[]>() {

            @Override
            protected byte[] initialValue() {
                return new byte[1024 * 1024];
            }

        };
        threadLocal.get();
    }

    /**
     * VM Args: -server -verbose:gc -Xms100M -Xmx100M -Xmn50M
     */
    @Test
    public void test1() throws Exception {
        ThreadLocal threadLocal = new ThreadLocal();
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(() ->
                threadLocal.set(new byte[1024 * 1024 * 10])
        );
        Thread.sleep(1000);
        System.gc();

        executor.submit(() ->
                threadLocal.remove()
        );
        Thread.sleep(1000);
        System.gc();
    }

    /**
     * VM Args: -server -verbose:gc -Xms100M -Xmx100M -Xmn50M
     */
    @Test
    public void testThreadLocal() {
        ThreadLocal threadLocal = new DataThreadLocal();
        threadLocal.set(new byte[1024 * 1024 * 10]);

        System.gc();

        threadLocal = null;
        // key被回收, value未被回收
        System.gc();
    }

    private class DataThreadLocal extends ThreadLocal {

        private byte[] data = new byte[1024 * 1024 * 10];

    }

    @Test
    public void test3() throws Exception {
        int length = 100;
        ThreadLocal[] threadLocals = new ThreadLocal[length];
        for (int i = 0; i < length; i++) {
            threadLocals[i] = new ThreadLocal();
            threadLocals[i].set(1);
        }
        System.in.read();
    }

}
