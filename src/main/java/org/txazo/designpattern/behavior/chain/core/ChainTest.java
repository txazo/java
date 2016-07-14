package org.txazo.designpattern.behavior.chain.core;

import org.junit.Test;

public class ChainTest {

    @Test
    public void test() {
        Chain chain = new Chain();
        chain.addHandler(new LessHandler());
        chain.addHandler(new MiddleHandler());
        chain.addHandler(new MassHandler());
        chain.handle(10);
        chain.handle(1000);
        chain.handle(100000);
    }

}
