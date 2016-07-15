package org.txazo.designpattern.structural.bridge;

/**
 * 宝马汽车
 */
public class BMCar extends Car {

    public BMCar(Road road) {
        super(road);
    }

    @Override
    public void ride() {
        System.out.println("宝马 ride on " + road.getName());
    }

}
