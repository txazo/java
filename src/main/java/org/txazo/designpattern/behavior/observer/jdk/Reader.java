package org.txazo.designpattern.behavior.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 */
public class Reader implements Observer {

    private String name;

    public Reader(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(name + " receive newspaper publish notify: " + arg);
    }

}
