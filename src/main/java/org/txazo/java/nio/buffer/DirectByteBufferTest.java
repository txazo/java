package org.txazo.java.nio.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * java.nio.DirectByteBuffer    直接内存字节缓冲区
 *
 * 1) 对比
 * ------------------------------------------------------------
 * -            HeapByteBuffer      DirectByteBuffer
 * -    内存        堆内存                 直接内存(减少一次copy)
 * -    内存分配     稍快                   稍慢
 * -    I/O        效率低                  效率高
 * -    内存回收    gc回收
 * ------------------------------------------------------------
 */
public class DirectByteBufferTest {

    @Test
    public void test1() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(10);
    }

}
