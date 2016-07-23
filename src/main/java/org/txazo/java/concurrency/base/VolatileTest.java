package org.txazo.java.concurrency.base;

import org.junit.Test;

import java.io.Serializable;

/**
 * volatile
 * <p>
 * 1. 保证可见性
 * 2. 不保证原子性
 */
public class VolatileTest {

    @Test
    public void testVolatile() {
        /**
         * 汇编代码
         *
         * lock ...
         * 1) 缓存一致性协议
         * 2) 缓存行数据写回主存
         * 3) 其它处理器的缓存行无效
         */
        node = new Node();
    }

    private static volatile Node node;

    private static class Node<V> implements Serializable {

        private V value;

    }

    /**
     * volatile追加字节
     */
    private static class PaddedNode<V> extends Node<V> {

        private Object o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, oa, ob, oc, od, oe;

    }

}
