package org.txazo.designpattern.creational.abstractfactory;

/**
 * 电脑工厂
 */
public interface ComputerFactory {

    /**
     * 生产CPU
     */
    public Cpu createCpu();

    /**
     * 生产主板
     */
    public Mainboard createMainboard();

}
