package org.txazo.jvm.object;

import org.txazo.jvm.object.size.ObjectSize;

public class ObjectSizeTest {

    private static void printObjectSize(Object obj) {
        System.out.println(ObjectSize.sizeOf(obj) + "\t" + obj.getClass().getName());
    }

    /**
     * VM Args: -XX:+UseCompressedOops -javaagent:/Users/txazo/TxazoProject/java/target/objectsize-agent.jar
     * VM Args: -XX:-UseCompressedOops -javaagent:/Users/txazo/TxazoProject/java/target/objectsize-agent.jar
     */
    public static void main(String[] args) {
        printObjectSize((byte) 1);
        printObjectSize(true);
        printObjectSize((short) 1);
        printObjectSize('1');
        printObjectSize(1);
        printObjectSize(1L);
        printObjectSize(1.0F);
        printObjectSize(1.0D);
        printObjectSize("1");
        printObjectSize(new int[2]);
    }

}
