package org.txazo.jvm.sa;

public class ToolTest {

    /**
     * VM Args: -server -Xms40M -Xmx40M -XX:+UseParallelGC -XX:OnOutOfMemoryError="sudo java -cp .:$JAVA_HOME/lib/sa-jdi.jar:/Users/txazo/TxazoProject/java/target/java-1.0.jar org.txazo.jvm.sa.PsHeapPrintTest %p > /Users/txazo/test/outofmemoryerror.log" -Dfile.encoding=UTF-8
     */
    public static void main(String[] args) {
        Byte[] longs = new Byte[40 * 1024 * 1024];
    }

}
