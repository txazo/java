package org.txazo.jvm.agent;

import java.lang.instrument.Instrumentation;

/**
 * Java Agent
 *
 * 实现下面两者之一的方法:
 * 1) public static void premain(String agentArgs)
 * 2) public static void premain(String agentArgs, Instrumentation inst)
 */
public class JavaAgent {

    /**
     * 在main()之前执行
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("javaagent premain ...");
        System.out.println("agentArgs: " + agentArgs);
    }

}
