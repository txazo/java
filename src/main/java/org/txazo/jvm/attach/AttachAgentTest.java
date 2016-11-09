package org.txazo.jvm.attach;

import com.sun.tools.attach.VirtualMachine;

public class AttachAgentTest {

    /**
     * Args: {pid} /Users/txazo/TxazoProject/java/target/attach-agent.jar
     */
    public static void main(String[] args) throws Exception {
        String pid = args[0];
        String agent = args[1];
        VirtualMachine vm = VirtualMachine.attach(pid);
        System.out.println(vm.getClass().getName());
        vm.loadAgent(agent);
        vm.detach();
    }

}
