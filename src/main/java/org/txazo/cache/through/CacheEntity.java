package org.txazo.cache.through;

import java.io.Serializable;

/**
 * 缓存实体
 */
public final class CacheEntity implements Serializable {

    private static final long serialVersionUID = 5265944642969357264L;

    // 空缓存
    public static final CacheEntity EMPTY = new CacheEntity();

}
