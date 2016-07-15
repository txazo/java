package org.txazo.designpattern.creational.factorymethod;

import org.junit.Test;

/**
 * 工厂方法模式
 */
public class FactoryMethodTest {

    @Test
    public void test() {
        CarFactory factory = new BCCarFactory();
        Car car = factory.createCar();
        System.out.println(car.getName());

        factory = new BMCarFactory();
        car = factory.createCar();
        System.out.println(car.getName());
    }

}
