package org.txazo.designpattern.behavior.observer.core;

/**
 * 被观察者
 */
public interface Subject {

    public void attach(Observer observer);

    public void detach(Observer observer);

    public void notifyObservers();

    public void change(String state);

}
