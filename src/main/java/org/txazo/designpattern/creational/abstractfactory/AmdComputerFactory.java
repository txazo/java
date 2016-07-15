package org.txazo.designpattern.creational.abstractfactory;

/**
 * AMD电脑工厂
 */
public class AmdComputerFactory implements ComputerFactory {

    @Override
    public Cpu createCpu() {
        return new AmdCpu();
    }

    @Override
    public Mainboard createMainboard() {
        return new AmdMainboard();
    }

}
