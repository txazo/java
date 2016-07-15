package org.txazo.designpattern.behavior.observer.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 */
public class ConcreteSubject implements Subject {

    private String state;

    // 观察者集合
    private List<Observer> observers = new ArrayList<Observer>();

    @Override
    public synchronized void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public synchronized void detach(Observer observer) {
        observers.remove(observer);
    }

    // 通知观察者
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }

    @Override
    public void change(String state) {
        this.state = state;
        notifyObservers();
    }

}
