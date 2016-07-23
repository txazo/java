package org.txazo.java;

import org.junit.Test;
import org.txazo.tool.util.watch.StopWatch;
import org.txazo.tool.util.watch.StopWatchTask;

import java.util.concurrent.atomic.AtomicLong;

/**
 * False Sharing伪共享
 *
 * @author txazo
 * @email txazo1218@163.com
 * @since 2015-08-31
 */
public class FalseSharingTest {

    private static final int COUNT = 100000000;

    @Test
    public void test() throws Throwable {
        StopWatch.newInstance().watch("FalseSharing", new StopWatchTask() {

            @Override
            public void execute() throws Throwable {
                try {
                    Thread[] threads = new Thread[10];
                    for (int i = 0; i < threads.length; i++) {
                        threads[i] = new Thread(new FalseSharing());
                    }
                    for (Thread thread : threads) {
                        thread.start();
                    }
                    for (Thread thread : threads) {
                        thread.join();
                    }
                } catch (Exception e) {
                }
            }

        }).watch("NoneFalseSharing", new StopWatchTask() {

            @Override
            public void execute() throws Throwable {
                try {
                    Thread[] threads = new Thread[10];
                    for (int i = 0; i < threads.length; i++) {
                        threads[i] = new Thread(new NoneFalseSharing());
                    }
                    for (Thread thread : threads) {
                        thread.start();
                    }
                    for (Thread thread : threads) {
                        thread.join();
                    }
                } catch (Exception e) {
                }
            }

        }).showTime();
    }

    private static class FalseSharing implements Runnable {

        private static AtomicLong[] longs = new AtomicLong[10];

        static {
            for (int i = 0; i < longs.length; i++) {
                longs[i] = new AtomicLong();
            }
        }

        @Override
        public void run() {
            int count = COUNT;
            while (--count > 0) {
                longs[count % longs.length].set(count);
            }
        }

    }

    private static class NoneFalseSharing implements Runnable {

        private static PaddingAtomicLong[] longs = new PaddingAtomicLong[10];

        static {
            for (int i = 0; i < longs.length; i++) {
                longs[i] = new PaddingAtomicLong();
            }
        }

        @Override
        public void run() {
            int count = COUNT;
            while (--count > 0) {
                longs[count % longs.length].set(count);
            }
        }

    }

    private static class PaddingAtomicLong extends AtomicLong {

        public volatile long p1, p2, p3, p4, p5, p6 = 7L;

    }

}
