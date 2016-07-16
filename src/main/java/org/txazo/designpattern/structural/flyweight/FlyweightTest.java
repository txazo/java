package org.txazo.designpattern.structural.flyweight;

import org.junit.Test;

/**
 * 享元模式
 */
public class FlyweightTest {

    @Test
    public void test() {
        CoffeeFactory factory = CoffeeFactory.getInstance();
        factory.getCoffee("摩卡").drink("A");
        factory.getCoffee("巧克力").drink("B");
        factory.getCoffee("巧克力").drink("C");
        factory.getCoffee("卡布奇诺").drink("D");
        factory.getCoffee("卡布奇诺").drink("E");
        factory.getCoffee("卡布奇诺").drink("F");
    }

}
