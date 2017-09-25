package org.txazo.jvm.gc;

import org.txazo.utils.MemoryUtils;

/**
 * 老年代GC
 */
public class MajorGCTest {

    /**
     * -XX:PretenureSizeThreshold参数只对SerialGC有效
     * VM Args: -XX:+UseSerialGC -Xms200m -Xmx200m -XX:NewRatio=1 -XX:SurvivorRatio=8 -XX:-UseTLAB -XX:PretenureSizeThreshold=1048576 -XX:+PrintGCDetails
     */
    public static void main(String[] args) throws Exception {
        for (; ; ) {
            byte[] bytes = new byte[1024 * 1024 * 10];
            Thread.sleep(1000);
            MemoryUtils.printHeapMemoryUsed();
        }
    }

}
