package org.txazo.tool.util.quartz.job;

/**
 * JobData
 */
public class JobData<V> implements Value<V> {

    private V value;

    public JobData(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}
