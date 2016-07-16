package org.txazo.designpattern.structural.facade;

import org.junit.Test;

/**
 * 门面模式
 */
public class FacadeTest {

    @Test
    public void test() {
        Facade facade = new Facade();
        facade.order();
        facade.facade();
    }

}
