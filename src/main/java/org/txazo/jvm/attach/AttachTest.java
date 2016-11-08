package org.txazo.jvm.attach;

import com.sun.tools.attach.VirtualMachine;

import java.lang.instrument.Instrumentation;

/**
 * 打包: agent-main.jar
 * Agent-Class: org.txazo.jvm.attach.AgentMainTest
 */
public class AttachTest {

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("agentmain ...");
    }

    /**
     * Args: 6317 /Users/txazo/Txazo/java/target/agent-main.jar
     */
    public static void main(String[] args) throws Exception {
        String pid = args[0];
        String agent = args[1];
        VirtualMachine vm = VirtualMachine.attach(pid);
        vm.loadAgent(agent);
        vm.detach();
    }

}
