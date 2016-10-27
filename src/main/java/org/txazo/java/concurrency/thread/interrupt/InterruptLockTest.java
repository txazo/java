package org.txazo.java.concurrency.thread.interrupt;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptLockTest {

    @Test
    public void test() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);
        final ReentrantLock lock = new ReentrantLock();
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    signal.await();
                    lock.lockInterruptibly();
                    int i = 1;
                    while (true) {
                        long l = i++ * 12345678987654321L * 98765432123456789L / 99999999999999999L;
                        System.out.println(i);
                    }
                } catch (InterruptedException e) {
                    System.out.println("isInterrupted: " + Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
            }

        });
        t.start();
        t.interrupt();
        signal.countDown();
        t.join();
        System.out.println("end");
    }

}
