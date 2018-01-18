package org.txazo.jvm.outofmemory;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出
 * <p>
 * VM Args: -server -verbose:gc -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/txazo/heapdump -XX:+PrintCommandLineFlags
 * <p>
 * java.lang.OutOfMemoryError: Java heap space
 */
public class HeapOutOfMemoryError {

    public static void main(String[] args) {
        List<byte[]> bytes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            bytes.add(new byte[1024 * 1024]);
        }
    }

}
