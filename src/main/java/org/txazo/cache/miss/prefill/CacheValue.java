package org.txazo.cache.miss.prefill;

import java.io.Serializable;

public class CacheValue implements Serializable {

    private static final long serialVersionUID = 6510381188446944077L;

    private int expireTime;
    private long endTime;
    private Object value;

    public CacheValue() {
    }

    public CacheValue(int expireTime, Object value) {
        this.expireTime = expireTime;
        this.endTime = expireTime + System.currentTimeMillis() / 1000;
        this.value = value;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public Object getValue() {
        return value;
    }

}
