package org.txazo.java.concurrency.thread;

import org.junit.Test;

/**
 * yield
 * <p>
 * 1) 让当前线程回到可运行状态
 *
 * @see Thread#yield()
 */
public class YieldTest {

    @Test
    public void test() throws InterruptedException {
        Thread t1 = new Thread(new YieldThread("Thread1"));
        Thread t2 = new Thread(new YieldThread("Thread2"));
        t1.start();
        t2.start();
        Thread.sleep(100);
    }

    private static class YieldThread implements Runnable {

        private String name;

        public YieldThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(name + " is running");
                Thread.yield();
            }
        }

    }

}
