package org.txazo.java.nio.buffer;

import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 内存映射文件
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws Exception {
        FileInputStream input = new FileInputStream("/Users/txazo/test/map.txt");
        FileChannel channel = input.getChannel();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, 32);
    }

}
