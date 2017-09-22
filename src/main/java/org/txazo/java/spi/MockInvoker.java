package org.txazo.java.spi;

public class MockInvoker implements Invoker {

    @Override
    public void invoke() {
        System.out.println("Mock Invoker");
    }

}
