package org.txazo.jvm.agent;

public class JavaAgentTest {

    /**
     * 格式: -javaagent:jarpath[=options]
     *
     * VM Args: -javaagent:/Users/txazo/TxazoProject/java/target/java-1.0.jar=hello
     *
     * jar包MANIFEST.MF要求:
     * 1) Premain-Class
     */
    public static void main(String[] args) {
        System.out.println("main execute ...");
    }

}
