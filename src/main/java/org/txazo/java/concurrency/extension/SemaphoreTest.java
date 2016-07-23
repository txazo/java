package org.txazo.java.concurrency.extension;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 * <p>
 * 1) 控制锁的并发数
 *
 * @see Semaphore
 * @see Semaphore#acquire()
 * @see Semaphore#release()
 */
public class SemaphoreTest {

    @Test
    public void test() throws InterruptedException {
        int total = 100;
        final Semaphore semaphore = new Semaphore(5);
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(total);
        for (int i = 0; i < total; i++) {
            final int j = i;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        start.await();

                        /** acquire()放在try外面, 确保acquire()抛出异常时, 不会执行finally中的release() */
                        semaphore.acquire();
                        try {
                            Thread.sleep(1000);
                            System.out.println("Thread " + j + " finished");
                        } finally {
                            /** release()放在finally块中, 确保try中抛出异常时也会执行 */
                            semaphore.release();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }

            }).start();
        }

        start.countDown();
        end.await();
    }

}
