package org.txazo.java.nio.buffer;

import org.junit.Assert;
import org.junit.Test;
import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

/**
 * java.nio.DirectByteBuffer    直接内存字节缓冲区
 *
 * 1) 对比
 * ------------------------------------------------------------
 * -            HeapByteBuffer      DirectByteBuffer
 * -    内存        堆内存                 直接内存(减少一次I/O copy)
 * -    内存分配     稍快                   稍慢
 * -    I/O        效率低                  效率高
 * ------------------------------------------------------------
 * 2) 直接内存分配和释放
 * -    分配      Unsafe.allocateMemory(size)     不受JVM限制
 * -    释放      Unsafe.freeMemory(address)
 * -    通过ByteBuffer.allocateDirect()申请直接内存时, 不超过-XX:MaxDirectMemorySize限制
 * ------------------------------------------------------------
 */
public class DirectByteBufferTest {

    /**
     * 直接内存回收
     *
     * @see java.nio.DirectByteBuffer.Deallocator
     */
    @Test
    public void testClean() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        Assert.assertEquals(1, Bits.getCount());
        Assert.assertEquals(1024, Bits.getMemoryUsed());
        Assert.assertEquals(1024, Bits.getTotalCapacity());
        ((DirectBuffer) buffer).cleaner().clean();
        Assert.assertEquals(0, Bits.getCount());
        Assert.assertEquals(0, Bits.getMemoryUsed());
        Assert.assertEquals(0, Bits.getTotalCapacity());
    }

    /**
     * VM Args: -XX:MaxDirectMemorySize=1024000
     */
    @Test(expected = java.lang.OutOfMemoryError.class)
    public void testMaxDirectMemorySize() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(2048000);
        ((DirectBuffer) buffer).cleaner().clean();
    }

}
