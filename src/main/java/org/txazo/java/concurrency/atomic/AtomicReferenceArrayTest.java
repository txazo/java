package org.txazo.java.concurrency.atomic;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * 原子操作类 － AtomicReferenceArray
 * <p>
 * 1) 原子操作引用类型数组
 * 2) int base(首地址偏移)
 * 3) int shift(位偏移)
 * 4) final Object[] array
 *
 * @see AtomicReferenceArray
 * @see AtomicReferenceArray#base
 * @see AtomicReferenceArray#shift
 * @see AtomicReferenceArray#array
 * @see AtomicReferenceArray#compareAndSet(int, Object, Object)
 */
public class AtomicReferenceArrayTest {

    @Test
    public void test() {
        AtomicReferenceArray array = new AtomicReferenceArray(1);
        array.set(0, "root");
        Assert.assertEquals("root", array.get(0));
        Assert.assertTrue(array.compareAndSet(0, "root", "admin"));
        Assert.assertEquals("admin", array.get(0));
    }

}
