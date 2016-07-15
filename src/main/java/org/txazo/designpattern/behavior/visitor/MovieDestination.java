package org.txazo.designpattern.behavior.visitor;

/**
 * 被访问者 - 电影院
 */
public class MovieDestination implements Destination {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void seeMovie() {
        System.out.println("看电影");
    }

}
