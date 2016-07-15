package org.txazo.designpattern.behavior.mediator;

/**
 * Tenant - 租客
 */
public class Tenant extends Customer {

    public Tenant(String name, RealEstateMediator mediator) {
        super(name, mediator);
        mediator.setTenant(this);
    }

    public void intendRent(String description) {
        mediator.sendMessage(description, this);
    }

}
