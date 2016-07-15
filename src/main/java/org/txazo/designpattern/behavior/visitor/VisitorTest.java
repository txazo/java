package org.txazo.designpattern.behavior.visitor;

import org.junit.Test;

public class VisitorTest {

    @Test
    public void test() {
        ObjectSturcture objectSturcture = new ObjectSturcture();
        objectSturcture.attach(new ParkDestination());
        objectSturcture.attach(new MovieDestination());
        objectSturcture.display(new ChildVisitor());
        objectSturcture.display(new AdultVisitor());
    }

}
