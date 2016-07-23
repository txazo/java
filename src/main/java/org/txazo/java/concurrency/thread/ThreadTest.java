package org.txazo.java.concurrency.thread;

import org.junit.Test;

/**
 * 线程
 * <p/>
 * 1) 一个线程只能运行在一个CPU上
 * 2) 初始化线程时最好设置下threadName, 便于使用jstack分析时进行问题排查
 *
 * @see Thread
 */
public class ThreadTest {

    @Test
    public void test() throws InterruptedException {
        Thread t1 = new MyThread();
        Thread t2 = new Thread(new MyRunnable());

        /* 线程启动 **/
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    /**
     * 线程实现方式一: 继承Thread
     */
    private static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("MyThread running ...");
        }

    }

    /**
     * 线程实现方式二: 实现Runnable
     */
    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("MyRunnable running ...");
        }

    }

//    public class Thread implements Runnable {
//
//        private Runnable target;
//
//        public Thread(Runnable target) {
//            this.target = target;
//        }
//
//        @Override
//        public void run() {
//            if (target != null) {
//                target.run();
//            }
//        }
//
//    }

}
