package org.txazo.designpattern.behavior.observer.jdk;

import java.util.Observable;

/**
 * 被观察者
 */
public class NewsPaper extends Observable {

    public void publish(String title) {
        setChanged();
        // 通知观察者
        notifyObservers(title);
    }

}
