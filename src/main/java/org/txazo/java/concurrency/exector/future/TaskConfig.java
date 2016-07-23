package org.txazo.java.concurrency.exector.future;

public class TaskConfig {

    private String id;
    private int timeout;
    private Object defaultResult;

    public TaskConfig(String id, int timeout, Object defaultResult) {
        this.id = id;
        this.timeout = timeout;
        this.defaultResult = defaultResult;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Object getDefaultResult() {
        return defaultResult;
    }

    public void setDefaultResult(Object defaultResult) {
        this.defaultResult = defaultResult;
    }

}
