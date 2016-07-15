package org.txazo.designpattern.creational.abstractfactory;

/**
 * 电脑组装
 */
public abstract class ComputerBuilder {

    public static void build(ComputerFactory factory) {
        Cpu cpu = factory.createCpu();
        Mainboard mainboard = factory.createMainboard();
        System.out.println("Build computer with " + cpu.getName() + " and " + mainboard.getName());
    }

}
