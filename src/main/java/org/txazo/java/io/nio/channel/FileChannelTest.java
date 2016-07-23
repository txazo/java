package org.txazo.java.io.nio.channel;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileChannel
 */
public class FileChannelTest {

    @Test
    public void test() throws IOException {
        FileChannel channel = new RandomAccessFile("/Users/txazo/t-beta.sh", "r").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = channel.read(buffer);
        while (read != -1) {
            buffer.flip();
            if (buffer.hasRemaining()) {
                System.out.println(new String(buffer.array()));
            }
            buffer.clear();
            read = channel.read(buffer);
        }
    }

}
