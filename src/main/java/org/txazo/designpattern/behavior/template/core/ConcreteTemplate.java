package org.txazo.designpattern.behavior.template.core;

/**
 * 具体模板
 */
public class ConcreteTemplate extends AbstractTemplate {

    @Override
    protected void abstractMethod() {
        System.out.println("ConcreteTemplate abstractMethod");
    }

}
