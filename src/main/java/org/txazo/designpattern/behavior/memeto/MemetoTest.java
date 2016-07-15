package org.txazo.designpattern.behavior.memeto;

import org.junit.Assert;
import org.junit.Test;

public class MemetoTest {

    @Test
    public void test() {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("txazo");
        caretaker.saveMemento(originator.createMemento());
        Assert.assertEquals(originator.getState(), "txazo");

        originator.setState("admin");
        Assert.assertEquals(originator.getState(), "admin");

        originator.restoreMemento(caretaker.getMemento());
        Assert.assertEquals(originator.getState(), "txazo");
    }

}
