package org.txazo.java.reference;

import org.junit.Assert;
import org.junit.Test;

import java.lang.management.ManagementFactory;
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
        Entity entity = new Entity();
        ReferenceQueue<Entity> queue = new ReferenceQueue<>();
        PhantomReference reference = new PhantomReference(entity, queue);
        Assert.assertFalse(reference.isEnqueued());

        entity = null;
        System.gc();
        Thread.sleep(100);
        Assert.assertTrue(reference.isEnqueued());
        System.out.println(getHeapMemoryUsage());

        queue.poll();
        reference = null;
        System.gc();
        System.out.println(getHeapMemoryUsage());
    }

    private static long getHeapMemoryUsage() {
        return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() >> 20;
    }

    private static class Entity {

        private byte[] b = new byte[1024 * 1024 * 100];

    }

}
