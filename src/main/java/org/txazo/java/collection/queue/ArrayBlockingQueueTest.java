package org.txazo.java.collection.queue;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ArrayBlockingQueue
 *
 * @see ArrayBlockingQueue
 */
public class ArrayBlockingQueueTest {

    private static volatile boolean running = true;
    private static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(5);

    private static class Producer implements Runnable {

        @Override
        public void run() {
            try {
                while (running) {
                    int i = RandomUtils.nextInt(1, 100);
                    blockingQueue.put(i);
                    System.out.println("Produce: " + i);

                    Thread.sleep(500);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static class Consumer implements Runnable {

        @Override
        public void run() {
            try {
                while (running) {
                    System.out.println("Consume: " + blockingQueue.take());
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test() throws InterruptedException {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
        Thread.sleep(100000);
        running = false;
    }

}
