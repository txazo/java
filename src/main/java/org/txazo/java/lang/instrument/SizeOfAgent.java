package org.txazo.java.lang.instrument;

import java.lang.instrument.Instrumentation;

public class SizeOfAgent {

    private static Instrumentation inst;

    public static void premain(String agentArgs, Instrumentation instP) {
        inst = instP;
    }

    public static long sizeOf(Object o) {
        return inst.getObjectSize(o);
    }

}
