package org.txazo.java.util.event;

import java.util.HashSet;
import java.util.Set;

public class Value {

    private Object value;
    private Set<ValueEventListener> valueEventListeners = new HashSet<ValueEventListener>();

    public Value(Object value) {
        this.value = value;
    }

    public void changeValue(Object value) {
        this.value = value;
        triggerListener(new ValueEvent(value));
    }

    public void addListener(ValueEventListener listener) {
        valueEventListeners.add(listener);
    }

    public void triggerListener(ValueEvent event) {
        for (ValueEventListener listener : valueEventListeners) {
            listener.valueChanged(event);
        }
    }

}
