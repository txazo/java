package org.txazo.java.reference;

import org.junit.Test;

/**
 * f类: 重写Object的finalize()方法的类
 *
 * @see java.lang.ref.FinalReference
 * @see java.lang.ref.Finalizer
 */
public class FinalReferenceTest {

    private static Entity instance = null;

    @Test
    public void test() throws Exception {
        Entity entity = new Entity(50);

        // 非f类对象, gc时直接回收
        entity = null;
        System.gc();
        MemoryUtils.printHeapMemoryUsed();

        entity = new FinalizeEntity(50);

        // f类对象, gc时先enqueue, 然后调用finalize()方法, 等待下次gc时回收
        entity = null;
        System.gc();
        // sleep, 等待finalize()执法完成
        Thread.sleep(100);
        MemoryUtils.printHeapMemoryUsed();

        // finalize()后, f类对象又被强引用, gc时不能被回收
        System.gc();
        MemoryUtils.printHeapMemoryUsed();

        // f类对象, gc时判断已被finalized, 直接回收
        instance = null;
        System.gc();
        MemoryUtils.printHeapMemoryUsed();
    }

    private static class Entity {

        private byte[] b;

        public Entity(int size) {
            this.b = new byte[1024 * 1024 * size];
        }

    }

    private static class FinalizeEntity extends Entity {

        public FinalizeEntity(int size) {
            super(size);
        }

        @Override
        protected void finalize() throws Throwable {
            instance = this;
            System.out.println("finalize");
        }

    }

}
