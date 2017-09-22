package org.txazo.java.nio.buffer;

import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * java.nio.Buffer  缓冲区
 *
 * 1) 字段
 * --------------------------------------------------
 * -    mark            记录position的标记
 * -    position        下一个读写位置
 * -    limit           读写的最大位置
 * -    capacity        总容量
 * --------------------------------------------------
 * -    mark <= position <= limit <= capacity
 * -    读写区间        position ~ limit
 * -    写模式下        limit = capacity
 * -    读模式下        limit = 最后一次写入position
 * --------------------------------------------------
 * 2) 操作
 * --------------------------------------------------
 * -    mark()          记录当前position到mark
 * -    reset()         从mark中恢复position
 * -
 * -    flip()          写模式切换到读模式
 * -    clear()         清除buffer, 切换到写模式, 可再次写
 * -
 * -    remaining()     剩余可读写的字节数
 * -    hasRemaining()  是否可继续读写
 * --------------------------------------------------
 */
public class BufferTest {

    @Test
    public void testMark() {
        ByteBuffer buffer = ByteBuffer.allocate(5);
        buffer.put(new byte[]{'a', 'b', 'c', 'd', 'e'});
        buffer.flip();
        buffer.get();
        buffer.mark();
        byte b1 = buffer.get();
        buffer.get();
        buffer.reset();
        byte b2 = buffer.get();
        Assert.assertEquals(b1, b2);
    }

    @Test
    public void testFlip() {
        byte[] bytes = new byte[]{'a', 'b', 'c', 'd', 'e'};
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(bytes);
        buffer.flip();
        byte[] readBytes = new byte[5];
        buffer.get(readBytes);
        Assert.assertArrayEquals(bytes, readBytes);
    }

    @Test
    public void testClear() {
        byte[] bytes = new byte[]{'a', 'b', 'c', 'd', 'e'};
        ByteBuffer buffer = ByteBuffer.allocate(5);
        buffer.put(bytes);
        Assert.assertFalse(buffer.hasRemaining());
        buffer.clear();
        Assert.assertTrue(buffer.hasRemaining());
        buffer.put(bytes);
        Assert.assertFalse(buffer.hasRemaining());
    }

}
