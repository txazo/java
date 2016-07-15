package org.txazo.designpattern.behavior.visitor;

/**
 * 被访问者
 */
public interface Destination {

    public void accept(Visitor visitor);

}
