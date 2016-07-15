package org.txazo.designpattern.behavior.visitor;

import java.util.ArrayList;
import java.util.List;

public class ObjectSturcture {

    private List<Destination> destinations = new ArrayList<Destination>();

    public void attach(Destination destination) {
        destinations.add(destination);
    }

    public void detach(Destination destination) {
        destinations.remove(destination);
    }

    public void display(Visitor visitor) {
        for (Destination destination : destinations) {
            destination.accept(visitor);
        }
    }

}
