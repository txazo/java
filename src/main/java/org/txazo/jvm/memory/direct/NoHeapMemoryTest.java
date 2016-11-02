package org.txazo.jvm.memory.direct;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.sun.misc.UnsafeHolder;

/**
 * 堆外内存
 */
public class NoHeapMemoryTest {

    public static void main(String[] args) throws InterruptedException {
        long size = 1024 * 1024 * 100;

        // 分配100M堆外内存
        long address = UnsafeHolder.unsafe.allocateMemory(size);

        Thread.sleep(10000);

        // 释放堆外内存
        UnsafeHolder.unsafe.freeMemory(address);
    }

    @Test
    public void test() {
        long address = UnsafeHolder.unsafe.allocateMemory(8);
        UnsafeHolder.unsafe.putLong(address, Long.MAX_VALUE);
        Assert.assertEquals(Long.MAX_VALUE, UnsafeHolder.unsafe.getLong(address));
    }

}
