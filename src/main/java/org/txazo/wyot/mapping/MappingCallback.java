package org.txazo.wyot.mapping;

public interface MappingCallback<S, D> {

    void callback(S s, D d);

}
