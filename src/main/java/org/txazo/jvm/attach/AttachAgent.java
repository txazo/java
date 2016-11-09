package org.txazo.jvm.attach;

import java.lang.instrument.Instrumentation;

public class AttachAgent {

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("attach sucess ...");
    }

}
