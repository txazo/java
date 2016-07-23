package org.txazo.java.concurrency.thread.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface ThreadPool {

    void execute(Runnable task);

    <T> Future<T> submit(Callable<T> task);

    void shutdown();

    void shutdownNow();

}
