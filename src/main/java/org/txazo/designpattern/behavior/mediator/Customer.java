package org.txazo.designpattern.behavior.mediator;

/**
 * 客户
 */
public abstract class Customer {

    protected String name;
    protected Mediator mediator;

    public Customer(String name, RealEstateMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public void receiveMessage(String message, String from) {
        System.out.println(name + " receive message from " + from + ": " + message);
    }

    public String getName() {
        return name;
    }

}
