package org.txazo.java.reference;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.utils.MemoryUtils;
import sun.misc.Cleaner;

import java.nio.ByteBuffer;

/**
 * Cleaner
 *
 * @see sun.misc.Cleaner
 * @see java.nio.DirectByteBuffer
 */
public class CleanerTest {

    private static Entity entity;

    @Test
    public void testDirectByteBuffer() throws Exception {
        // 分配10m堆外内存
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 10);

        // buffer持有强引用, gc时不被回收
        System.gc();
        MemoryUtils.printDirectMemoryUsed();

        // DirectByteBuffer.cleaner, gc时调用clean()方法释放堆外内存
        buffer = null;
        System.gc();
        // 等待clean()方法执行完
        Thread.sleep(100);
        MemoryUtils.printDirectMemoryUsed();
    }

    @Test
    public void testCleaner() throws Exception {
        entity = new Entity(50);
        Cleaner cleaner = Cleaner.create(entity, new Runnable() {

            @Override
            public void run() {
                System.out.println("clean");
            }

        });

        System.gc();
        Assert.assertFalse(cleaner.isEnqueued());
        MemoryUtils.printHeapMemoryUsed();

        entity = null;
        System.gc();
        Thread.sleep(100);
        // Cleaner不会enqueue
        Assert.assertFalse(cleaner.isEnqueued());
        MemoryUtils.printHeapMemoryUsed();
    }

    private static class Entity {

        private byte[] b;

        public Entity(int size) {
            this.b = new byte[1024 * 1024 * size];
        }

    }

}
