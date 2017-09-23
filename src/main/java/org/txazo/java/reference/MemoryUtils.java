package org.txazo.java.reference;

import java.lang.management.ManagementFactory;

public abstract class MemoryUtils {

    public static void printHeapMemoryUsed() {
        long used = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() >> 20;
        System.out.println("Heap used: " + used + "M");
    }

}
