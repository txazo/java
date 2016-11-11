package org.txazo.jvm.object;

import org.txazo.jvm.object.layout.ObjectLayout;

import java.util.HashMap;

public class ObjectLayoutTest {

    /**
     * VM Args: -XX:+UseCompressedOops -javaagent:/Users/txazo/TxazoProject/java/target/objectsize-agent.jar
     * VM Args: -XX:-UseCompressedOops -javaagent:/Users/txazo/TxazoProject/java/target/objectsize-agent.jar
     */
    public static void main(String[] args) {
        ObjectLayout.printLayout((byte) 1);
        ObjectLayout.printLayout(true);
        ObjectLayout.printLayout((short) 1);
        ObjectLayout.printLayout('1');
        ObjectLayout.printLayout(1);
        ObjectLayout.printLayout(1L);
        ObjectLayout.printLayout(1.0F);
        ObjectLayout.printLayout(1.0D);
        ObjectLayout.printLayout("1");
        ObjectLayout.printLayout(new int[3]);
        ObjectLayout.printLayout(new long[3]);
        ObjectLayout.printLayout(new HashMap());
    }

}
