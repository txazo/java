package org.txazo.java.util.event;

import java.util.EventObject;

public class ValueEvent extends EventObject {

    private static final long serialVersionUID = 1815774735342521764L;

    public ValueEvent(Object source) {
        super(source);
    }

}
