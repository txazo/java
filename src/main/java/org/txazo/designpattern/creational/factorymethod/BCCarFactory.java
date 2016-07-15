package org.txazo.designpattern.creational.factorymethod;

/**
 * 奔驰汽车工厂
 */
public class BCCarFactory implements CarFactory {

    @Override
    public Car createCar() {
        return new BCCar();
    }

}
