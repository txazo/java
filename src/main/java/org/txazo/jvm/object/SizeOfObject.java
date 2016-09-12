package org.txazo.jvm.object;

import java.lang.instrument.Instrumentation;

/**
 * 对象内存大小计算
 */
public class SizeOfObject {

    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation instrumentation) {
        SizeOfObject.instrumentation = instrumentation;
    }

    public static long sizeOf(Object obj) {
        return instrumentation.getObjectSize(obj);
    }

}
