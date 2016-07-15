package org.txazo.designpattern.behavior.state;

import org.junit.Test;

public class StateTest {

    @Test
    public void test() {
        State onState = new OnState();
        State offState = new OffState();
        Context context = new Context(onState);
        context.request();
        context.changeState(offState);
        context.request();
    }

}
