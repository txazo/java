package org.txazo.designpattern.behavior.chain.core;

/**
 * 职责链模式－链表实现
 */
public abstract class AbstractHandler implements Handler {

    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

}
