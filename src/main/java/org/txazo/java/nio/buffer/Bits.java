package org.txazo.java.nio.buffer;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.sun.misc.UnsafeHolder;
import sun.misc.JavaNioAccess;
import sun.misc.SharedSecrets;
import sun.misc.VM;

import java.nio.ByteBuffer;

/**
 * 直接内存管理
 *
 * @see java.nio.Bits
 */
public class Bits {

    private static final JavaNioAccess.BufferPool bufferPool = SharedSecrets.getJavaNioAccess().getDirectBufferPool();

    /**
     * 直接内存数量
     */
    public static long getCount() {
        return bufferPool.getCount();
    }

    /**
     * 直接内存总容量
     */
    public static long getTotalCapacity() {
        return bufferPool.getTotalCapacity();
    }

    /**
     * 直接内存占用内存大小
     */
    public static long getMemoryUsed() {
        return bufferPool.getMemoryUsed();
    }

    /**
     * 直接内存最大值
     *
     * -XX:MaxDirectMemorySize=1024000
     */
    public static long maxDirectMemory() {
        return VM.maxDirectMemory();
    }

    /**
     * VM Args: -XX:MaxDirectMemorySize=1024000
     */
    @Test
    public void testMaxDirectMemory() {
        Assert.assertEquals(1024000, maxDirectMemory());
    }

    /**
     * survivor=25m
     *
     * VM Args: -server -Xms200m -Xmx200m -XX:NewRatio=1 -XX:SurvivorRatio=2
     */
    @Test
    public void testMaxDirectMemory2() {
        long xms = 200 * 1024 * 1024;
        long survivor = 25 * 1024 * 1024;
        Assert.assertEquals(xms - survivor, VM.maxDirectMemory());
    }

    /**
     * 直接内存分配按页对齐
     *
     * VM Args: -Dsun.nio.PageAlignDirectMemory=true
     */
    @Test
    public void testPageAlignDirectMemory() {
        ByteBuffer.allocateDirect(10);
        Assert.assertTrue(VM.isDirectMemoryPageAligned());
        Assert.assertNotEquals(getTotalCapacity(), getMemoryUsed());
        Assert.assertEquals(getTotalCapacity() + UnsafeHolder.unsafe.pageSize(), getMemoryUsed());
    }

    /**
     * 直接内存分配不按页对齐
     *
     * VM Args: -Dsun.nio.PageAlignDirectMemory=false
     */
    @Test
    public void testPageAlignDirectMemory2() {
        ByteBuffer.allocateDirect(10);
        Assert.assertFalse(VM.isDirectMemoryPageAligned());
        Assert.assertEquals(getTotalCapacity(), getMemoryUsed());
    }

}
