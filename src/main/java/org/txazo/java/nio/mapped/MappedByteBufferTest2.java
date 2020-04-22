package org.txazo.java.nio.mapped;

import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest2 {

    @Test
    public void test() throws Exception {
        RandomAccessFile file = new RandomAccessFile("/Users/txazo/test/map_read_write.txt", "rw");
        FileChannel channel = file.getChannel();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);

        for (int i = 0; i < 1000; i++) {
            buffer.putChar('a');
            Thread.sleep(1000);
            if ((i % 10) == 0) {
                buffer.force();
            }
        }

        System.in.read();
    }

}
