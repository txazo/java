package org.txazo.java.util.event;

import java.util.EventObject;

public class ValueEvent extends EventObject {

    private static final long serialVersionUID = 1815774735342521764L;

    private final long timestamp;

    public ValueEvent(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }

    public final long getTimestamp() {
        return timestamp;
    }

}
