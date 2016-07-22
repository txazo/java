package org.txazo.tool.util.quartz.job;

/**
 * JobCallback
 */
public interface JobCallback<V> extends Value<V> {

    public void callback(V v) throws Exception;

}
