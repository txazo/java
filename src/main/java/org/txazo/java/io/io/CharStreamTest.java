package org.txazo.java.io.io;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.nio.CharBuffer;

public class CharStreamTest {

    private static final String fileName = "/Users/txazo/test/char.txt";

    @Test
    public void testWrite() throws Exception {
        Writer writer = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
        writer.write("abcd你好");
        IOUtils.closeQuietly(writer);
    }

    @Test
    public void testRead() throws Exception {
        Reader reader = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
        CharBuffer buffer = CharBuffer.allocate(1024);
        reader.read(buffer);
        IOUtils.closeQuietly(reader);
    }

}
