package org.txazo.tool.util.quartz.job;

/**
 * JobRemover
 */
public interface JobRemover<V> extends Value<V> {

    public boolean canRemove(V v);

}
