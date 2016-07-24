package org.txazo.jvm.compile;

public class LoopCompile {

    private static final int COUNT = 15000;

    private static void loop() {
        int k = 0;
        for (int i = 0; i < COUNT; i++) {
            k += i;
        }
    }

    private static int method(int number) {
        for (int i = 0; i < COUNT; i++) {
            number += i;
        }
        return number;
    }

    private static void loopMethod() {
        int k = 0;
        for (int i = 0; i < COUNT; i++) {
            k += method(i);
        }
    }

    public static void main(String[] args) {
        loop();
        for (int i = 0; i < COUNT; i++) {
            loopMethod();
        }
    }

}
