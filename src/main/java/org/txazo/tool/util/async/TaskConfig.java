package org.txazo.tool.util.async;

/**
 * TaskConfig
 */
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

    public int getTimeout() {
        return timeout;
    }

    public Object getDefaultResult() {
        return defaultResult;
    }

}
