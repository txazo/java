package org.txazo.designpattern.structural.bridge;

/**
 * 奔驰汽车
 */
public class BCCar extends Car {

    public BCCar(Road road) {
        super(road);
    }

    @Override
    public void ride() {
        System.out.println("奔驰 ride on " + road.getName());
    }

}
