package org.txazo.designpattern.structural.bridge;

/**
 * 汽车
 */
public abstract class Car {

    // 道路
    protected Road road;

    public Car(Road road) {
        this.road = road;
    }

    public abstract void ride();

}
