package org.txazo.designpattern.behavior.visitor;

/**
 * 访问者 - 小孩
 */
public class ChildVisitor implements Visitor {

    @Override
    public void visit(ParkDestination destination) {
        System.out.println("Child Visit: 公园");
        destination.play();
    }

    @Override
    public void visit(MovieDestination destination) {
        System.out.println("Child Visit: 电影院");
        destination.seeMovie();
    }

}
