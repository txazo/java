package org.txazo.java.concurrency.atomic;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子操作类 － AtomicInteger
 * <p>
 * 1) 原子操作int
 * 2) long valueOffset(类的字段偏移)
 * 3) volatile int value
 *
 * @see AtomicInteger
 * @see AtomicInteger#value
 * @see AtomicInteger#valueOffset
 * @see AtomicInteger#compareAndSet(int, int)
 */
public class AtomicIntegerTest {

    @Test
    public void test() {
        AtomicInteger integer = new AtomicInteger(1);
        Assert.assertEquals(1, integer.get());
        integer.set(10);
        Assert.assertEquals(10, integer.get());
        Assert.assertTrue(integer.compareAndSet(10, 100));
        Assert.assertEquals(100, integer.get());
    }

}
