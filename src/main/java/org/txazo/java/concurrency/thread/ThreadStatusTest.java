package org.txazo.java.concurrency.thread;

import java.io.IOException;
import java.util.concurrent.locks.LockSupport;

/**
 * 线程状态
 * <pre>
 * 1) NEW: 初始状态
 * 2) RUNNABLE: 运行状态, 包括运行(RUNNING)和就绪(READY)两种状态
 * 3) BLOCKED: 阻塞状态
 *      1) synchronized
 * 4) WAITING: 等待状态
 *      1) Object.wait()
 *      2) Thread.join()
 *      3) LockSupport.park()
 * 5) TIMED_WAITING: 超时等待状态
 *      1) Object.wait(timeout)
 *      2) Thread.join(timeout)
 *      3) Thread.sleep(timeout)
 *      4) LockSupport.parkNanos(timeout)
 *      5) LockSupport.parkUntil(timeout)
 * 6) TERMINATED: 终止状态
 * </pre>
 * <pre>
 * 1) 可通过jstack工具查看运行时的线程状态
 * </pre>
 *
 * @see Thread.State
 * @see Thread#getState()
 */
public class ThreadStatusTest {

//    jstack

//    RUNNING
//    runnable
//    java.lang.Thread.State: RUNNABLE

//    READY
//    waiting on condition
//    java.lang.Thread.State: RUNNABLE

//    synchronized
//    waiting for monitor entry
//    java.lang.Thread.State: BLOCKED (on object monitor)

//    Object.wait()或Thread.join()
//    in Object.wait()
//    java.lang.Thread.State: WAITING (on object monitor)

//    LockSupport.park()
//    waiting on condition
//    java.lang.Thread.State: WAITING (parking)

//    Thread.sleep(timeout)
//    waiting on condition
//    java.lang.Thread.State: TIMED_WAITING (sleeping)

//    Object.wait(timeout)或Thread.join(timeout)
//    in Object.wait()
//    java.lang.Thread.State: TIMED_WAITING (on object monitor)

//    LockSupport.parkNanos(timeout)或LockSupport.parkUntil(timeout)
//    waiting on condition
//    java.lang.Thread.State: TIMED_WAITING (parking)

    public static void main(String[] args) throws IOException, InterruptedException {
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//            }
//
//        }, "NEW");

//        for (int i = 0; i < 8; i++) {
//            startThread(new Runnable() {
//
//                @Override
//                public void run() {
//                    int i = 0;
//                    for (; ; ) {
//                        i++;
//                    }
//                }
//
//            }, "RUNNABLE " + i);
//        }

//        Thread t = startThread(new Runnable() {
//
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronized (this) {
//                    System.out.println("This is a BLOCKED Thread");
//                }
//            }
//
//        }, "BLOCKED");
//
//        synchronized (t) {
//            Thread.sleep(10000000);
//        }

//        Thread t = startThread(new Runnable() {
//
//            @Override
//            public void run() {
//                Object lock = new Object();
//                synchronized (lock) {
//                    try {
//                        lock.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        }, "WAITING 1");
//
//        startThread(new Runnable() {
//
//            @Override
//            public void run() {
//                try {
//                    t.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }, "WAITING 2");
//
//        startThread(new Runnable() {
//
//            @Override
//            public void run() {
//                LockSupport.park(this);
//            }
//
//        }, "WAITING 3");

        startThread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "TIMED_WAITING 1");

                Thread t = startThread(new Runnable() {

            @Override
            public void run() {
                Object lock = new Object();
                synchronized (lock) {
                    try {
                        lock.wait(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, "TIMED_WAITING 2");

        startThread(new Runnable() {

            @Override
            public void run() {
                try {
                    t.join(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "TIMED_WAITING 3");

        startThread(new Runnable() {

            @Override
            public void run() {
                LockSupport.parkUntil(this,System.currentTimeMillis() + 100000);
            }

        }, "TIMED_WAITING 4");

        System.in.read();
    }

    private static Thread startThread(Runnable runnable, String name) {
        Thread t = new Thread(runnable, name);
        t.start();
        return t;
    }

}
