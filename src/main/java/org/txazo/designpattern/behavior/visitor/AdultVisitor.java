package org.txazo.designpattern.behavior.visitor;

/**
 * 访问者 - 成人
 */
public class AdultVisitor implements Visitor {

    @Override
    public void visit(ParkDestination destination) {
        System.out.println("Adult Visit: 公园");
        destination.play();
    }

    @Override
    public void visit(MovieDestination destination) {
        System.out.println("Adult Visit: 电影院");
        destination.seeMovie();
    }

}
