package org.txazo.java.util.event;

import org.junit.Test;
import org.txazo.test.AbstractTest;

public class EventTest extends AbstractTest {

    @Test
    public void test() {
        Value value = new Value(50);
        value.addListener(new ValueEventListener() {

            @Override
            public void valueChanged(ValueEvent event) {
                logger.info("Value changed to " + event.getSource());
            }

        });
        value.changeValue(100);
    }

}
