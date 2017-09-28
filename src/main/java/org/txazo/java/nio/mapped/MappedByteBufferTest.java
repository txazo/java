package org.txazo.java.nio.mapped;

import org.junit.Test;
import org.txazo.utils.MemoryUtils;
import sun.nio.ch.DirectBuffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 内存映射文件
 *
 * 1) 适合映射大文件
 * 2) 三种模式: 读、读写、私有
 * 3) 内存为虚拟内存, 非物理内存
 */
public class MappedByteBufferTest {

    @Test
    public void testRead() throws Exception {
        FileInputStream input = new FileInputStream("/Users/txazo/test/map_read.txt");
        FileChannel channel = input.getChannel();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, 10);
    }

    @Test
    public void testWrite() throws Exception {
        FileOutputStream output = new FileOutputStream("/Users/txazo/test/map_write.txt");
        FileChannel channel = output.getChannel();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 10);
    }

    @Test
    public void testReadWrite() throws Exception {
        RandomAccessFile file = new RandomAccessFile("/Users/txazo/test/map_read_write.txt", "rw");
        FileChannel channel = file.getChannel();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 1024 * 1024);
        for (int i = 0; i < 1024 * 1024; i++) {
            buffer.putInt(i);
        }

        MemoryUtils.printHeapMemoryUsed();
        MemoryUtils.printDirectMemoryUsed();

        buffer.force();
        ((DirectBuffer) buffer).cleaner().clean();

        MemoryUtils.printHeapMemoryUsed();
        MemoryUtils.printDirectMemoryUsed();

        file.close();
    }

}
