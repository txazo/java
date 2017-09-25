package org.txazo.java.reference;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.utils.MemoryUtils;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用
 *
 * @see java.lang.ref.PhantomReference
 */
public class PhantomReferenceTest {

    /**
     * 新生代20m, 老年代80m
     *
     * VM Args: -server -Xms100m -Xmx100m -XX:NewRatio=4
     */
    @Test(expected = OutOfMemoryError.class)
    public void test() throws Exception {
        Entity entity = new Entity(40);
        ReferenceQueue queue = new ReferenceQueue<>();
        PhantomReference reference = new PhantomReference(entity, queue);

        System.gc();
        // PhantomReference.get()始终返回null
        Assert.assertNull(reference.get());
        Assert.assertFalse(reference.isEnqueued());
        MemoryUtils.printHeapMemoryUsed();

        entity = null;
        System.gc();
        Thread.sleep(100);
        // gc后, 虚引用对象enqueue, 但未被回收
        Assert.assertTrue(reference.isEnqueued());
        MemoryUtils.printHeapMemoryUsed();

        // 虚引用对象出队, gc后仍未被回收, 网上说此处会被回收, 被误导了
        queue.poll();
        System.gc();
        Assert.assertFalse(reference.isEnqueued());
        MemoryUtils.printHeapMemoryUsed();

        // 新分配50m内存, 老年代内存不够, 直接抛出OutOfMemoryError
        entity = new Entity(50);

        // 此处可见, PhantomReference中引用对象在gc时只会enqueue, 但占用的内存不会被回收
    }

    private static class Entity {

        private byte[] b;

        public Entity(int size) {
            this.b = new byte[1024 * 1024 * size];
        }

    }

}
