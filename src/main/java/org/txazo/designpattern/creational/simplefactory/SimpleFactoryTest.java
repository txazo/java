package org.txazo.designpattern.creational.simplefactory;

import org.junit.Test;

/**
 * 简单工厂模式
 */
public class SimpleFactoryTest {

    @Test
    public void test() {
        Fruit fruit = FruitFactory.createFruit("apple");
        fruit.eat();

        fruit = FruitFactory.createFruit("orange");
        fruit.eat();
    }

}
