package org.txazo.jvm.object.size;

import java.lang.instrument.Instrumentation;

/**
 * 打包: objectsize-agent.jar
 */
public class ObjectSize {

    private static int referenceSize = 0;
    private static Instrumentation inst;

    public static void premain(String agentArgs, Instrumentation inst) {
        ObjectSize.inst = inst;

        long size = sizeOf(1);
        referenceSize = size == 16 ? 4 : 8;
    }

    public static long sizeOf(Object obj) {
        return inst.getObjectSize(obj);
    }

    public static int referenceSize() {
        return referenceSize;
    }

}
