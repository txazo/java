package org.txazo.designpattern.behavior.chain.core;

public class MiddleHandler extends AbstractHandler {

    @Override
    public void handle(int quantity) {
        if (quantity > 100 && quantity < 10000) {
            System.out.println("MiddleHandler with quantity " + quantity);
        } else if (next != null) {
            next.handle(quantity);
        }
    }

}
