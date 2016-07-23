package org.txazo.java.concurrency.thread;

import org.junit.Assert;
import org.junit.Test;

/**
 * ThreadLocalMap
 * <p>
 * 1) Thread有实例变量ThreadLocal.ThreadLocalMap
 * 2) ThreadLocal的原理: 获取currentThread, 操作currentThread中的ThreadLocal.ThreadLocalMap
 *
 * @see Thread#threadLocals
 * @see ThreadLocal
 * @see ThreadLocal.ThreadLocalMap
 */
public class ThreadLocalMapTest {

    private static ThreadLocal<Integer> count = new ThreadLocal<Integer>();

    @Test
    public void test() {
        count.set(1);
        Assert.assertEquals(1, (int) count.get());
    }

}
