package org.txazo.java.concurrency.extension;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 * <p>
 * 1) 一个或多个线程等待其它线程完成操作
 * 2) countDown(): 计数减一
 * 3) await(): 阻塞当前线程, 直到计数为0
 * 4) 一个线程调用countDown() happen-before 另一个线程调用await()
 *
 * @see CountDownLatch
 * @see CountDownLatch#await()
 * @see CountDownLatch#countDown()
 */
public class CountDownLatchTest {

    @Test
    public void test() throws InterruptedException {
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int j = i;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        begin.await();
                        System.out.println("Thread " + j + " begin");
                        Thread.sleep(RandomUtils.nextInt(1000, 5000));
                        System.out.println("Thread " + j + " finish");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        /** countDown()建议放在finally中执行 */
                        end.countDown();
                    }
                }

            }).start();
        }

        Thread.sleep(2000);
        System.out.println("begin");
        /** 控制多个线程同时开始执行 */
        begin.countDown();
        /** 等待多个线程执行完毕 */
        end.await();
        System.out.println("end");
    }

}
