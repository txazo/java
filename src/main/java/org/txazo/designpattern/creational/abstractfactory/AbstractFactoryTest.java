package org.txazo.designpattern.creational.abstractfactory;

import org.junit.Test;

/**
 * 抽象工厂模式
 */
public class AbstractFactoryTest {

    @Test
    public void test() {
        ComputerBuilder.build(new AmdComputerFactory());
        ComputerBuilder.build(new IntelComputerFactory());
    }

}
