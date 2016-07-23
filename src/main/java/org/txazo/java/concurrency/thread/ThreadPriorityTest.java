package org.txazo.java.concurrency.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * 线程优先级
 * <p>
 * 1) 优先级从1到10(最高优先级)
 *
 * @see Thread#priority
 */
public class ThreadPriorityTest {

    @Test
    public void test() throws InterruptedException {
        CountDownLatch countDown = new CountDownLatch(1);
        for (int i = 1; i <= 20; i++) {
            Thread t = new Thread(new PriorityThread(i, countDown));
            t.setPriority((i - 1) % 10 + 1);
            t.start();
        }

        countDown.countDown();

        Thread.sleep(5000);
        PriorityThread.running = false;
        Thread.sleep(1000);
    }

    private static class PriorityThread implements Runnable {

        private static volatile boolean running = true;

        private int i;
        private int count = 0;
        private CountDownLatch countDown;

        public PriorityThread(int priority, CountDownLatch countDown) {
            this.i = priority;
            this.countDown = countDown;
        }

        @Override
        public void run() {
            try {
                countDown.await();
                while (running) {
                    Thread.yield();
                    count++;
                }
                System.out.println("thread " + i + " count " + count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

//    public class Thread implements Runnable {
//
//        /* 线程优先级 **/
//        private int priority;
//
//        public Thread() {
//            Thread parent = currentThread();
//            /* 继承父线程优先级 **/
//            this.priority = parent.getPriority();
//            ThreadGroup g;
//            if ((g = getThreadGroup()) != null) {
//                if (newPriority > g.getMaxPriority()) {
//                    /* 不超过线程组最大优先级 **/
//                    newPriority = g.getMaxPriority();
//                }
//                setPriority0(priority = newPriority);
//            }
//        }
//
//    }

}
