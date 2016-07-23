package org.txazo.java.concurrency.thread;

import org.junit.Test;

/**
 * 守护线程
 * <p>
 * 1) Java线程分为用户线程和守护线程
 * 2) 守护线程: 后台运行的线程
 * 3) 虚拟机中所有用户线程退出, 虚拟机也会退出, 守护线程会立即终止
 *
 * @see Thread#daemon
 */
public class ThreadDaemonTest {

    @Test
    public void test() throws InterruptedException {
        Thread t = new Thread(new DaemonThread());
        /* 设置线程为守护线程 **/
        t.setDaemon(true);
        t.start();
        Thread.sleep(10);
    }

    private static class DaemonThread implements Runnable {

        @Override
        public void run() {
            int count = 0;
            try {
                while (true) {
                    System.out.println("DaemonThread running " + count++);
                }
            } catch (Exception e) {
                /* 虚拟机退出, 不会执行 **/
                e.printStackTrace();
            } finally {
                /* 虚拟机退出, 不会执行 **/
            }
        }

    }

//    public class Thread implements Runnable {
//
//        /* 守护线程标识 **/
//        private boolean daemon = false;
//
//        public Thread() {
//            Thread parent = currentThread();
//            /* 继承父线程的daemon **/
//            this.daemon = parent.isDaemon();
//        }
//
//    }

}
