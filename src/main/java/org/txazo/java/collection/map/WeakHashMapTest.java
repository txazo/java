package org.txazo.java.collection.map;

import org.junit.Test;
import org.txazo.utils.MemoryUtils;

import java.util.WeakHashMap;

/**
 * WeakHashMap
 * <p>
 * 1) key为弱键
 * 2) key不被引用时, 会被GC回收, 同时被添加到ReferenceQueue
 * 3) 操作WeakHashMap时, 同步table和queue, 删除弱键key对应的value
 */
public class WeakHashMapTest {

    @Test
    public void test() throws Exception {
        WeakHashMap<Object, Object> map = new WeakHashMap<>();

        map.put(new Entity(30), new Entity(50));
        MemoryUtils.printHeapMemoryUsed();

        // 第一次gc, 回收弱键
        System.gc();
        Thread.sleep(100);
        MemoryUtils.printHeapMemoryUsed();

        // 弱键对应的value置为null
        map.size();

        // 第二次gc, 回收弱键对应的value
        System.gc();
        Thread.sleep(100);
        MemoryUtils.printHeapMemoryUsed();
    }

    private static class Entity {

        private byte[] b;

        public Entity(int size) {
            this.b = new byte[1024 * 1024 * size];
        }

    }

}
