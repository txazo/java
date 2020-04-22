package org.txazo.wyot.future;

public abstract class FutureTask<V> {

    private final String name;

    public FutureTask(String name) {
        this.name = name;
    }

    public abstract V invoke() throws Exception;

    public abstract void callback(V value) throws Exception;

    public String getName() {
        return name;
    }

}
