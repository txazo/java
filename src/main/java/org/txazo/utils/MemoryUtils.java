package org.txazo.utils;

import sun.misc.SharedSecrets;

import java.lang.management.ManagementFactory;

public abstract class MemoryUtils {

    public static void printHeapMemoryUsed() {
        long used = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() >> 20;
        System.err.println("Heap Memory used: " + used + "M");
    }

    public static void printDirectMemoryUsed() {
        long used = SharedSecrets.getJavaNioAccess().getDirectBufferPool().getMemoryUsed() >> 20;
        System.err.println("Direct Memory used: " + used + "M");
    }

}
