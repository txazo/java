package org.txazo.designpattern.behavior.template.core;

import org.junit.Test;

public class TemplateTest {

    @Test
    public void test() {
        AbstractTemplate template = new ConcreteTemplate();
        template.templateMethod();
    }

}
