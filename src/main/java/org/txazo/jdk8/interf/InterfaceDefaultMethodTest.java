package org.txazo.jdk8.interf;

import org.junit.Test;

/**
 * 接口默认方法
 */
public class InterfaceDefaultMethodTest {

    @Test
    public void test() {
        new DefaulableImpl().execute();
        new DefaulableImpl2().execute();
    }

    private interface Defaulable {

        default void execute() {
            System.out.println("Defaulable execute");
        }

    }

    private class DefaulableImpl implements Defaulable {
    }

    private class DefaulableImpl2 implements Defaulable {

        @Override
        public void execute() {
            System.out.println("DefaulableImpl2 execute");
        }
    }

}
