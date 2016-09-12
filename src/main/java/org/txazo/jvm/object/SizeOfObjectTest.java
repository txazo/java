package org.txazo.jvm.object;

/**
 * 对象头: 8字节(32位) 16字节(64位)
 * 基本数据类型:
 * 引用类型: 4字节(32位) 8字节(64位)
 * 对齐填充: 8字节对齐
 * 指针压缩:
 */
public class SizeOfObjectTest {

    /**
     * -XX:+UseCompressedOops -XX:-UseCompressedOops
     * VM Args: -javaagent:/Users/txazo/TxazoProject/java/target/java-1.0.jar
     */
    public static void main(String[] args) {
        // 对象头
        System.out.println("sizeOf(new Object()) = " + SizeOfObject.sizeOf(new Object()));
        System.out.println("sizeOf(new A()) = " + SizeOfObject.sizeOf(new A()));
    }

    static class A {

        private Object obj;

    }

}
