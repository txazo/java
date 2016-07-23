package org.txazo.java.concurrency.exector.future;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class AsyncTask {

    private static final ExecutorService taskPool = new ThreadPoolExecutor(50, 50, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(500));

    private Map<String, TaskConfig> configMap = new HashMap<String, TaskConfig>();
    private Map<String, Future> futureMap = new HashMap<String, Future>();

    private void checkTaskConfig(TaskConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("TaskConfig must not be null");
        }
    }

    public synchronized void submit(TaskConfig config, Runnable task) {
        checkTaskConfig(config);
        configMap.put(config.getId(), config);
        futureMap.put(config.getId(), taskPool.submit(task));
    }

    public synchronized <T> void submit(TaskConfig config, Callable<T> task) {
        checkTaskConfig(config);
        configMap.put(config.getId(), config);
        futureMap.put(config.getId(), taskPool.submit(task));
    }

    public <T> T getResult(String id, Class<T> clazz) throws Exception {
        return (T) getResult(id);
    }

    public Object getResult(String id) throws Exception {
        return getResult(configMap.get(id));
    }

    private Object getResult(TaskConfig config) throws Exception {
        if (config == null) {
            return null;
        }

        Future future = futureMap.get(config.getId());
        if (future != null) {
            return future.get(config.getTimeout(), TimeUnit.MILLISECONDS);
        }
        return config.getDefaultResult();
    }

}
