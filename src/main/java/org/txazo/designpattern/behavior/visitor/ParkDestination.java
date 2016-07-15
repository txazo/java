package org.txazo.designpattern.behavior.visitor;

/**
 * 被访问者 - 公园
 */
public class ParkDestination implements Destination {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void play() {
        System.out.println("游船赏花");
    }

}
