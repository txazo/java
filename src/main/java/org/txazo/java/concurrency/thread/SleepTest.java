package org.txazo.java.concurrency.thread;

import org.junit.Test;

/**
 * 线程休眠
 * <p>
 * 1) 当前线程休眠一段时间
 * 2) 不会释放对象锁
 *
 * @see Thread#sleep(long)
 * @see Thread#sleep(long, int)
 */
public class SleepTest {

    @Test
    public void test() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    "main" prio=5 tid=0x00007fb05b008000 nid=0x1303 waiting on condition [0x0000000109369000]
//    java.lang.Thread.State: TIMED_WAITING (sleeping)

}
