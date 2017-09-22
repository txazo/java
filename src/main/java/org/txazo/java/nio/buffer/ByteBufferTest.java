package org.txazo.java.nio.buffer;

import org.junit.Test;

/**
 * java.nio.ByteBuffer  字节缓冲区
 *
 * 1) 字段
 * ----------------------------------------
 * -    byte[] hb       字节数组
 * -    offset
 * -    bigEndian       大端法
 * ----------------------------------------
 * 2) 创建ByteBuffer
 * ----------------------------------------
 * -    ByteBuffer.allocate()       堆内存
 * -    ByteBuffer.allocateDirect() 直接内存
 * -    ByteBuffer.wrap(byte[])
 * ----------------------------------------
 * 3) get/put操作
 * ----------------------------------------
 * -    get()
 * -    get(int)
 * -    get(byte[])
 * -    getInt()    getLong()   ...
 * -    put(byte)
 * -    put(int, byte)
 * -    put(byte[])
 * -    putInt()    putLong()   ...
 * ----------------------------------------
 */
public class ByteBufferTest {

    @Test
    public void test() {

    }

}
