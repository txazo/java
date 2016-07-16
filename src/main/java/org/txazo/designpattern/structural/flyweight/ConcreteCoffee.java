package org.txazo.designpattern.structural.flyweight;

public class ConcreteCoffee implements Coffee {

    private String flavor;

    public ConcreteCoffee(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public void drink(String name) {
        System.out.println(name + " drink " + flavor + " coffee");
    }

}
