package org.txazo.java.concurrency.thread;

import org.junit.Test;

/**
 * join
 * <p>
 * 1) 等待目标线程执行完毕
 * 2) join依赖wait来实现
 * 3) synchronized void join(long millis)
 * 4) 目标线程执行完毕, 会调用notifyAll()唤醒在join上等待的线程
 *
 * @see Thread#join()
 * @see Thread#join(long)
 * @see Thread#join(long, int)
 */
public class JoinTest {

    @Test
    public void testJoin() throws InterruptedException {
        Thread t = new Thread(new JoinThread(), "Join");
        t.start();
        t.join();
    }

//    "main" prio=5 tid=0x00007fa846001800 nid=0x1303 in Object.wait() [0x0000000106bbc000]
//    java.lang.Thread.State: WAITING (on object monitor)

    @Test
    public void testJoinTime() throws InterruptedException {
        Thread t = new Thread(new JoinThread(), "Join Time");
        t.start();
        t.join(20000);
    }

//    "main" prio=5 tid=0x00007f8252802000 nid=0x1303 in Object.wait() [0x0000000102f6d000]
//    java.lang.Thread.State: TIMED_WAITING (on object monitor)

    private static class JoinThread implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

//    public class Thread implements Runnable {
//
//        public final void join() throws InterruptedException {
//            join(0);
//        }
//
//        /** synchronized同步方法 */
//        public final synchronized void join(long millis)
//                throws InterruptedException {
//            long base = System.currentTimeMillis();
//            long now = 0;
//
//            if (millis < 0) {
//                throw new IllegalArgumentException("timeout value is negative");
//            }
//
//            if (millis == 0) {
//                while (isAlive()) {
//                    wait(0);
//                }
//            } else {
//                while (isAlive()) {
//                    long delay = millis - now;
//                    if (delay <= 0) {
//                        break;
//                    }
//                    wait(delay);
//                    now = System.currentTimeMillis() - base;
//                }
//            }
//        }
//
//    }

}
