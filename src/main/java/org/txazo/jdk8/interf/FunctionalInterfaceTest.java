package org.txazo.jdk8.interf;

import org.junit.Test;

/**
 * 函数式接口
 */
public class FunctionalInterfaceTest {

    @Test
    public void test() {
        execute(() -> System.out.println("execute"));
    }

    private void execute(Executor executor) {
        executor.execute();
    }

    @FunctionalInterface
    private interface Executor {

        void execute();

    }

}
