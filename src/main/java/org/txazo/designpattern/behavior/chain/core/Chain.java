package org.txazo.designpattern.behavior.chain.core;

public class Chain implements Handler {

    private Handler chain;

    public synchronized void addHandler(AbstractHandler handler) {
        handler.setNext(chain);
        chain = handler;
    }

    @Override
    public void handle(int quantity) {
        chain.handle(quantity);
    }

}
