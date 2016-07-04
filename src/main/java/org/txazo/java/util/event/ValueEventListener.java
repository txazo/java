package org.txazo.java.util.event;

import java.util.EventListener;

public interface ValueEventListener extends EventListener {

    public void valueChanged(ValueEvent event);

}
