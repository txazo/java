package org.txazo.java.reference;

import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用
 *
 * @see java.lang.ref.PhantomReference
 */
public class PhantomReferenceTest {

    @Test
    public void test() throws Exception {
        ReferenceQueue<Entity> queue = new ReferenceQueue<>();
        PhantomReference reference = new PhantomReference(new Entity(50), queue);

        System.gc();
        Thread.sleep(100);
        // gc后, 虚引用对象enqueued, 但未被回收
        Assert.assertTrue(reference.isEnqueued());
        MemoryUtils.printHeapMemoryUsed();

        queue.remove();
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
