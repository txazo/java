package org.txazo.java.concurrency.thread;

import org.junit.Test;

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

}
