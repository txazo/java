package org.txazo.java.concurrency.extension;

import org.junit.Test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 同步屏障
 * <p>
 * 1) 一组线程相互等待, 直到所有线程到达屏障
 * 2) 可循环使用
 *
 * @see CyclicBarrier
 * @see CyclicBarrier#await()
 * @see CyclicBarrier#reset()
 */
public class CyclicBarrierTest {

    @Test
    public void test() throws Exception {
        final CyclicBarrier barrier = new CyclicBarrier(2, new BarrierCallback());
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    barrier.await();
                    System.out.println("My Thread running");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();

        barrier.await();
        System.out.println("Main Thread running");

        barrier.reset();
        try {
            barrier.await(2, TimeUnit.SECONDS);
        } catch (Exception e) {
        }
        System.out.println("Main Thread end");
    }

    private static class BarrierCallback implements Runnable {

        @Override
        public void run() {
            System.out.println("BarrierCallback begin");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("BarrierCallback end");
        }

    }

}
