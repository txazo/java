package org.txazo.java.reference;

import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Final引用 + Weak引用
 */
public class FinalAndWeakReferenceTest {

    @Test
    public void testFinalAndWeak() throws Exception {
        // entity同时被Final引用和弱引用
        FinalizeEntity entity = new FinalizeEntity(50);
        WeakReference<FinalizeEntity> reference = new WeakReference<>(entity, new ReferenceQueue<>());

        /**
         * gc后
         *
         * Final引用对象enqueue并执行f类的finalize()
         * 弱引用对象enqueue
         * entity未被回收
         */
        entity = null;
        System.gc();
        Thread.sleep(100);
        // 弱引用关联的referent已被清除
        Assert.assertNull(reference.get());
        Assert.assertTrue(reference.isEnqueued());
        MemoryUtils.printHeapMemoryUsed();

        // 再次gc, entity被回收
        System.gc();
        MemoryUtils.printHeapMemoryUsed();
    }

    private static class FinalizeEntity {

        private byte[] b;

        public FinalizeEntity(int size) {
            this.b = new byte[1024 * 1024 * size];
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize");
        }

    }

}
