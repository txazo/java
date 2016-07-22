package org.txazo.tool.util.ehcache;

import net.sf.ehcache.CacheManager;

import java.util.HashMap;
import java.util.Map;

/**
 * EhCacheManager
 */
public class EhCacheManager {

    private Map<String, EhCache> ehCacheMap = new HashMap<String, EhCache>();

    private CacheManager cacheManager;

    EhCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public synchronized EhCache getEhCache(String cacheName) {
        if (ehCacheMap.containsKey(cacheName)) {
            return ehCacheMap.get(cacheName);
        }

        EhCache ehCache = new EhCache(cacheManager.getCache(cacheName));
        ehCacheMap.put(cacheName, ehCache);
        return ehCache;
    }

}
