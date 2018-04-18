package org.txazo.java.concurrency.base;

public class VolatileTest2 {

    private static int i = 1;

    /**
     * VM Args: -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:-Inline -XX:CompileCommand=print,*VolatileTest2.test
     */
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        i++;
    }

}
