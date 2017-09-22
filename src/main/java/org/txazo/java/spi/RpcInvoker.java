package org.txazo.java.spi;

public class RpcInvoker implements Invoker {

    @Override
    public void invoke() {
        System.out.println("Rpc Invoker");
    }

}
