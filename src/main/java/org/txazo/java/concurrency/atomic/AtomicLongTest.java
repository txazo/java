package org.txazo.java.concurrency.atomic;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 原子操作类 － AtomicLong
 * <p>
 * 1) 原子操作long
 * 2) long valueOffset(类的字段偏移)
 * 3) volatile long value
 *
 * @see AtomicLong
 * @see AtomicLong#value
 * @see AtomicLong#valueOffset
 * @see AtomicLong#compareAndSet(long, long)
 */
public class AtomicLongTest {

    @Test
    public void test() {
        AtomicLong atomicLong = new AtomicLong(1);
        Assert.assertEquals(1, atomicLong.get());
        atomicLong.set(10);
        Assert.assertEquals(10, atomicLong.get());
        Assert.assertTrue(atomicLong.compareAndSet(10, 100));
        Assert.assertEquals(100, atomicLong.get());
    }

}
