package org.txazo.designpattern.creational.factorymethod;

/**
 * 宝马汽车工厂
 */
public class BMCarFactory implements CarFactory {

    @Override
    public Car createCar() {
        return new BMCar();
    }

}
