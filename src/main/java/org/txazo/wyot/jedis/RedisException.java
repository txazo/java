package org.txazo.wyot.jedis;

public class RedisException extends RuntimeException {

    private static final long serialVersionUID = 9043987883136983498L;

    public RedisException() {
    }

    public RedisException(String message) {
        super(message);
    }

    public RedisException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisException(Throwable cause) {
        super(cause);
    }

}
