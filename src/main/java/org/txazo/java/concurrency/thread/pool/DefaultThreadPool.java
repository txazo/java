package org.txazo.java.concurrency.thread.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 简单的线程池
 */
public class DefaultThreadPool implements ThreadPool {

    private volatile int corePoolSize;
    private volatile int maximumPoolSize;
    private volatile long keepAliveTime;
    private final BlockingQueue<Runnable> workQueue;

    public DefaultThreadPool(int corePoolSize, int maximumPoolSize, int keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.workQueue = workQueue;
    }


    @Override
    public void execute(Runnable task) {

    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return null;
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void shutdownNow() {

    }

    private static class CallableAdapter<V> implements Runnable, Callable<V> {

        private Callable<V> callable;

        public CallableAdapter(Callable<V> callable) {
            this.callable = callable;
        }

        @Override
        public V call() throws Exception {
            return callable.call();
        }

        @Override
        public void run() {
            try {
                call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static class Worker {

    }

}
