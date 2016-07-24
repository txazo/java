package org.txazo.jvm.compile;

public class CompileInfo {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
