package org.txazo.designpattern.behavior.chain.core;

public class LessHandler extends AbstractHandler {

    @Override
    public void handle(int quantity) {
        if (quantity <= 100) {
            System.out.println("LessHandler with quantity " + quantity);
        } else if (next != null) {
            next.handle(quantity);
        }
    }

}
