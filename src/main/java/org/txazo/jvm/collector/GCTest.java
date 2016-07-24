package org.txazo.jvm.collector;

public class GCTest {

    // VM Args: -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -verbose:gc
    public static void main(String[] args) {
        byte[] byte1 = new byte[2 * 1024 * 1024];
        byte[] byte2 = new byte[2 * 1024 * 1024];
        byte[] byte3 = new byte[2 * 1024 * 1024];
        byte[] byte4 = new byte[2 * 1024 * 1024];
    }

}
