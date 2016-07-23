package org.txazo.java.concurrency.atomic;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 原子操作类 － AtomicBoolean
 * <p>
 * 1) 原子操作boolean
 * 2) long valueOffset(类的字段偏移)
 * 3) volatile int value(1为true, 0为false)
 *
 * @see AtomicBoolean
 * @see AtomicBoolean#value
 * @see AtomicBoolean#valueOffset
 * @see AtomicBoolean#compareAndSet(boolean, boolean)
 */
public class AtomicBooleanTest {

    @Test
    public void test() {
        AtomicBoolean bool = new AtomicBoolean(true);
        Assert.assertTrue(bool.get());
        bool.set(false);
        Assert.assertFalse(bool.get());
        Assert.assertTrue(bool.compareAndSet(false, true));
        Assert.assertTrue(bool.get());
    }

}
