package org.txazo.tool.util.quartz.job;

/**
 * JobExecutor
 *
 * @author txazo
 * @email txazo1218@163.com
 * @since 15.07.2015
 */
public interface JobExecutor<V> extends Value<V> {

    public boolean canExecute(V v);

}
