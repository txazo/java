package org.txazo.jvm.gc;

import org.txazo.utils.MemoryUtils;

/**
 * 新生代GC
 */
public class YoungGCTest {

    /**
     * VM Args: -server -Xms200m -Xmx200m -XX:NewRatio=1 -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=20971520 -XX:+PrintGCDetails
     */
    public static void main(String[] args) throws Exception {
        for (; ; ) {
            byte[] bytes = new byte[1024 * 1024 * 10];
            Thread.sleep(1000);
            MemoryUtils.printHeapMemoryUsed();
        }
    }

}
