package org.txazo.cache.through;

/**
 * 缓存查询结果
 */
public class CacheResponse {

    public static final CacheResponse RESPONSE_EMPTY = new CacheResponse(CacheStatus.EMPTY);
    public static final CacheResponse RESPONSE_MISS = new CacheResponse(CacheStatus.MISS);

    private CacheStatus status;
    private Object value;

    public CacheResponse(CacheStatus status) {
        this.status = status;
    }

    public CacheResponse(CacheStatus status, Object value) {
        this.status = status;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public boolean isOk() {
        return status == CacheStatus.OK;
    }

    public boolean isEmpty() {
        return status == CacheStatus.EMPTY;
    }

    public boolean isMiss() {
        return status == CacheStatus.MISS;
    }

}
