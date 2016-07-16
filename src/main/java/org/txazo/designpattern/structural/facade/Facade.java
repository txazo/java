package org.txazo.designpattern.structural.facade;

/**
 * 门面
 */
public class Facade {

    private Waiter waiter;
    private Cooker cooker;
    private Cashier cashier;

    public Facade() {
        waiter = new Waiter();
        cooker = new Cooker();
        cashier = new Cashier();
    }

    public void order() {
        waiter.order();
    }

    public void facade() {
        waiter.order();
        waiter.submitOrder();
        cooker.cook();
        waiter.service();
        cashier.cash();
    }

}
