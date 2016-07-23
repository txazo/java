package org.txazo.java.concurrency.atomic;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子操作类 － AtomicReference
 * <p>
 * 1) 原子操作对象
 * 2) long valueOffset(类的字段偏移)
 * 3) volatile V value
 *
 * @see AtomicReference
 * @see AtomicReference#value
 * @see AtomicReference#valueOffset
 * @see AtomicReference#compareAndSet(Object, Object)
 */
public class AtomicReferenceTest {

    @Test
    public void test() {
        AtomicReference<String> reference = new AtomicReference<String>();
        Assert.assertNull(reference.get());
        reference.set("root");
        Assert.assertEquals("root", reference.get());
        Assert.assertTrue(reference.compareAndSet("root", "admin"));
        Assert.assertEquals("admin", reference.get());
    }

}
