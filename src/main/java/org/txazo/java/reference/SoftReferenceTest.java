package org.txazo.java.reference;

import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * 软引用
 *
 * @see java.lang.ref.SoftReference
 */
public class SoftReferenceTest {

    /**
     * 新生代20m, 老年代80m
     *
     * VM Args: -server -Xms100m -Xmx100m -XX:NewRatio=4
     */
    @Test
    public void test() {
        // 软引用对象40m
        SoftReference<Entity> reference = new SoftReference<>(new Entity(40), new ReferenceQueue<>());

        // 内存足够, 软引用对象不会被回收
        System.gc();
        Assert.assertNotNull(reference.get());
        Assert.assertFalse(reference.isEnqueued());
        MemoryUtils.printHeapMemoryUsed();

        // 50m, 老年代内存不够, 触发gc, 软引用对象被回收并enqueue
        Entity newEntity = new Entity(50);
        Assert.assertNull(reference.get());
        Assert.assertTrue(reference.isEnqueued());
        MemoryUtils.printHeapMemoryUsed();
    }

    private static class Entity {

        private byte[] b;

        public Entity(int size) {
            this.b = new byte[1024 * 1024 * size];
        }

    }

}
