package org.txazo.designpattern.behavior.observer.core;

/**
 * 观察者
 */
public class ConcreteObserver implements Observer {

    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Object object) {
        System.out.println(name + " receive update data: " + object);
    }

}
