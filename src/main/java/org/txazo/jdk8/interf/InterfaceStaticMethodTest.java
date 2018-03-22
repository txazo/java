package org.txazo.jdk8.interf;

import org.junit.Test;

/**
 * 接口静态方法
 */
public class InterfaceStaticMethodTest {

    @Test
    public void test() {
        Statics.execute();
    }

    private interface Statics {

        static void execute() {
            System.out.println("Statics execute");
        }

    }

    private class StaticsImpl implements Statics {
    }

}
