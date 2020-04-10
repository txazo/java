package org.txazo.utils;

import sun.misc.SharedSecrets;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.List;

public abstract class MemoryUtils {

    public static void printHeapMemoryUsed() {
        long used = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() >> 20;
        System.err.println("Heap Memory used: " + used + "M");
    }

    public static void printDirectMemoryUsed() {
        long used = SharedSecrets.getJavaNioAccess().getDirectBufferPool().getMemoryUsed() >> 20;
        System.err.println("Direct Memory used: " + used + "M");
    }

    public static void printHeapMemoryDetail() {
        List<MemoryPoolMXBean> mxBeans = ManagementFactory.getMemoryPoolMXBeans();
        mxBeans.forEach(item ->
                System.err.print(String.format("%s: %dM(%dM)\t\t", item.getName(), toMB(item.getUsage().getUsed()), toMB(item.getUsage().getMax())))
        );
        System.err.println();
    }

    private static long toMB(long bytes) {
        return bytes / 1024 / 1024;
    }

}
