package org.txazo.java.concurrency.atomic;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 原子操作类 － AtomicReferenceFieldUpdater
 * <p>
 * 1) 原子操作对象的引用类型字段
 *
 * @see AtomicReferenceFieldUpdater
 * @see AtomicReferenceFieldUpdater#newUpdater(Class, Class, String)
 * @see AtomicReferenceFieldUpdater.AtomicReferenceFieldUpdaterImpl
 * @see AtomicReferenceFieldUpdater.AtomicReferenceFieldUpdaterImpl#offset (类的字段偏移)
 * @see AtomicReferenceFieldUpdater.AtomicReferenceFieldUpdaterImpl#tclass
 * @see AtomicReferenceFieldUpdater.AtomicReferenceFieldUpdaterImpl#vclass
 */
public class AtomicReferenceFieldUpdaterTest {

    @Test
    public void test() {
        Entity entity = new Entity(null);
        AtomicReferenceFieldUpdater<Entity, String> fieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Entity.class, String.class, "value");
        fieldUpdater.set(entity, "root");
        Assert.assertEquals("root", entity.getValue());
        Assert.assertTrue(fieldUpdater.compareAndSet(entity, "root", "admin"));
        Assert.assertEquals("admin", entity.getValue());
    }

    private static class Entity {

        /**
         * 字段必须为public volatile类型
         */
        public volatile String value;

        public Entity(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

}
