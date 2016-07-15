package org.txazo.designpattern.creational.simplefactory;

/**
 * 水果工厂
 */
public abstract class FruitFactory {

    public static Fruit createFruit(String name) {
        if ("apple".equalsIgnoreCase(name)) {
            return new Apple();
        }
        return new Orange();
    }

}
