package org.txazo.java.concurrency.thread;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.Stack;

/**
 * 等待/通知
 * <p>
 * 1) Object.wait(): 线程在目标对象上进入等待状态
 * 2) Object.notify(): 唤醒目标对象上的一个等待线程
 * 3) Object.notifyAll(): 唤醒目标对象上的所有等待线程
 * 4) 执行wait()、notify()、notifyAll()时, 必须保证已经获取到对象的锁, 否则抛IllegalMonitorStateException
 *
 * @see Object#wait()
 * @see Object#wait(long)
 * @see Object#wait(long, int)
 * @see Object#notify()
 * @see Object#notifyAll()
 */
public class WaitNotifyTest {

    @Test
    public void test() throws InterruptedException {
        Product product = new Product();
        Thread put = new Thread(new PutThread(product));
        Thread get = new Thread(new GetThread(product));
        put.start();
        get.start();
        Thread.sleep(300000);
    }

    /**
     * 生产者/消费者模式
     */
    private static class Product {

        private static final int MAX = 5;

        private Stack<String> container = new Stack<String>();

        /**
         * 生产
         */
        public synchronized void put(String p) {
            while (container.size() >= MAX) {
                try {
                    /** wait时释放锁 */
                    wait();
                    /** 被notify/notifyAll唤醒后, 重新竞争锁 */
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            container.push(p);
            /** notify/notifyAll时不释放锁 */
            notify();
        }

        /**
         * 消费
         */
        public synchronized String get() {
            while (container.isEmpty()) {
                try {
                    /** wait时释放锁 */
                    wait();
                    /** 被notify/notifyAll唤醒后, 重新竞争锁 */
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            /** notify/notifyAll时不释放锁 */
            notify();
            return container.pop();
        }

    }

    private static class PutThread implements Runnable {

        private Product product;

        public PutThread(Product product) {
            this.product = product;
        }

        @Override
        public void run() {
            while (true) {
                String p = String.valueOf(RandomUtils.nextInt(1, 100));
                System.out.println("put " + p);
                product.put(p);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static class GetThread implements Runnable {

        private Product product;

        public GetThread(Product product) {
            this.product = product;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println("get " + product.get());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
