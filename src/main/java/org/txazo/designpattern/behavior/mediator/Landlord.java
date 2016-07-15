package org.txazo.designpattern.behavior.mediator;

/**
 * Landlord - 房东
 */
public class Landlord extends Customer {

    public Landlord(String name, RealEstateMediator mediator) {
        super(name, mediator);
        mediator.setLandlord(this);
    }

    public void publishRent(String description) {
        mediator.sendMessage(description, this);
    }

}
