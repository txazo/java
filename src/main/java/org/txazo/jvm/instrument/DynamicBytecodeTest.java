package org.txazo.jvm.instrument;

public class DynamicBytecodeTest {

    /**
     * VM Args: -javaagent:/Users/txazo/TxazoProject/java/target/dynamic-bytecode-agent.jar
     */
    public static void main(String[] args) throws Exception {
        DynamicClass dynamic = new DynamicClass();
        for (; ; ) {
            dynamic.load();
            Thread.sleep(3000);
        }
    }

}
