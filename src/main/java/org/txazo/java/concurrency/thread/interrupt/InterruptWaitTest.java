package org.txazo.java.concurrency.thread.interrupt;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

public class InterruptWaitTest {

    @Test
    public void test() throws InterruptedException {
        final Thread main = Thread.currentThread();
        final long startTime = System.currentTimeMillis();
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
//                    synchronized (this) {
//                        wait();
//                    }
//                    main.join();
                } catch (InterruptedException e) {
                    System.out.println("interruptTime: " + (System.currentTimeMillis() - startTime));
                    System.out.println(Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
            }

        });

        t.start();
        Thread.sleep(RandomUtils.nextInt(100, 2000));
        t.interrupt();
        System.out.println("waitTime: " + (System.currentTimeMillis() - startTime));
        t.join();
    }

}
