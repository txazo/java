package org.txazo.designpattern.creational.abstractfactory;

/**
 * Intel电脑工厂
 */
public class IntelComputerFactory implements ComputerFactory {

    @Override
    public Cpu createCpu() {
        return new IntelCpu();
    }

    @Override
    public Mainboard createMainboard() {
        return new IntelMainboard();
    }

}
