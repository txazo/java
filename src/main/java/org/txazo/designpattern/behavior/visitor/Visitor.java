package org.txazo.designpattern.behavior.visitor;

/**
 * 访问者
 */
public interface Visitor {

    public void visit(ParkDestination destination);

    public void visit(MovieDestination destination);

}
