package org.txazo.designpattern.behavior.chain.core;

public class MassHandler extends AbstractHandler {

    @Override
    public void handle(int quantity) {
        if (quantity >= 10000) {
            System.out.println("MassHandler with quantity " + quantity);
        } else if (next != null) {
            next.handle(quantity);
        }
    }

}
