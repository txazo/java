package org.txazo.designpattern.structural.bridge;

import org.junit.Test;

/**
 * 桥接模式
 */
public class BridgeTest {

    @Test
    public void test() {
        Car car = new BMCar(new TarRoad());
        car.ride();

        car = new BMCar(new HighwayRoad());
        car.ride();

        car = new BCCar(new TarRoad());
        car.ride();

        car = new BCCar(new HighwayRoad());
        car.ride();
    }

}
