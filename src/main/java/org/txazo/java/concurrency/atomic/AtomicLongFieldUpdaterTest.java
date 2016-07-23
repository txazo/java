package org.txazo.java.concurrency.atomic;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * 原子操作类 － AtomicLongFieldUpdater
 * <p>
 * 1) 原子操作对象的long类型字段
 *
 * @see AtomicLongFieldUpdater
 * @see AtomicLongFieldUpdater#newUpdater(Class, String)
 * @see AtomicLongFieldUpdater.CASUpdater
 * @see AtomicLongFieldUpdater.LockedUpdater
 * @see AtomicLongFieldUpdater.LockedUpdater#offset (类的字段偏移)
 * @see AtomicLongFieldUpdater.LockedUpdater#tclass
 */
public class AtomicLongFieldUpdaterTest {

    @Test
    public void test() {
        Entity entity = new Entity(1);
        AtomicLongFieldUpdater fieldUpdater = AtomicLongFieldUpdater.newUpdater(Entity.class, "value");
        Assert.assertEquals(1, entity.getValue());
        fieldUpdater.set(entity, 10);
        Assert.assertEquals(10, entity.getValue());
        Assert.assertTrue(fieldUpdater.compareAndSet(entity, 10, 100));
        Assert.assertEquals(100, entity.getValue());
    }

    private static class Entity {

        /**
         * 字段必须为public volatile类型
         */
        public volatile long value;

        public Entity(long value) {
            this.value = value;
        }

        public long getValue() {
            return value;
        }

    }

}
