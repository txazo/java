package org.txazo.jvm.memory.direct;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

public class DirectByteBufferTest {

    public static void main(String[] args) {
        int size = 1024 * 1024 * 100;

        // 分配堆内内存
        ByteBuffer.allocate(size);

        // 分配堆外内存
        DirectBuffer directBuffer = (DirectBuffer) ByteBuffer.allocateDirect(size);
        // 释放堆外内存
        directBuffer.cleaner().clean();
    }

}
