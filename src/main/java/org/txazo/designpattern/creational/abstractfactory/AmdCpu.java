package org.txazo.designpattern.creational.abstractfactory;

/**
 * AMD主板
 */
public class AmdCpu implements Cpu {

    @Override
    public String getName() {
        return "AmdCpu";
    }

}
