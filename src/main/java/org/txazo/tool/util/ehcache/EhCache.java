package org.txazo.tool.util.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * EhCache
 */
public class EhCache {

    private Cache cache;

    EhCache(Cache cache) {
        this.cache = cache;
    }

    public void put(Object key, Object value) {
        cache.put(new Element(key, value));
    }

    public Object get(Object key) {
        Element element = cache.get(key);
        return element != null ? element.getObjectValue() : null;
    }

    public void remove(Object key) {
        cache.remove(key);
    }

}
