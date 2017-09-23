package org.txazo.java.reference;

import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 弱引用
 *
 * @see java.lang.ref.WeakReference
 */
public class WeakReferenceTest {

    @Test
    public void test() throws Exception {
        Entity entity = new Entity(50);
        ReferenceQueue<Entity> queue = new ReferenceQueue<>();
        WeakReference<Entity> reference = new WeakReference<>(entity, queue);

        Assert.assertNotNull(reference.get());
        Assert.assertFalse(reference.isEnqueued());
        MemoryUtils.printHeapMemoryUsed();

        entity = null;
        System.gc();
        Thread.sleep(100);
        // gc后, 弱引用对象被回收并enqueue
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
