package org.txazo.wyot.jedis;

public abstract class RedisCommand<T> {

    private String command;

    private Object[] args;

    public RedisCommand(String command, Object... args) {
        this.command = command;
        this.args = args;
    }

    abstract T execute();

    public String getCommand() {
        return command;
    }

    public Object[] getArgs() {
        return args;
    }

}
