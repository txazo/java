package org.txazo.java.nio.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

public class HeapByteBufferTest {

    @Test
    public void test1() {
        ByteBuffer buffer = ByteBuffer.allocate(15);
        buffer.put((byte) 1);
        buffer.putShort((short) 1);
        buffer.putInt(1);
        buffer.putLong(1);
    }

}
