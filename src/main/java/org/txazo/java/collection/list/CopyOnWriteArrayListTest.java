package org.txazo.java.collection.list;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList
 * <p>
 * 1) 线程安全
 * 2) 读写分离, 读不加锁, 写加锁ReentrantLock + volatile
 * 3) 适合读多写少的并发场景
 * 4) 尽量批量添加, 初始化大小减少扩容
 * 5) 迭代器Iterator是线程安全的, 因为数组元素不会被修改
 * <p>
 * 缺点:
 * 1) 内存占用: 两个数组对象, 数组占用内存较大时, 可能造成频繁的GC
 * 2) 数据一致性: 保证数据的最终一致性, 但不保证数据的实时一致性
 */
public class CopyOnWriteArrayListTest {

    // VM Args: -server -Xmx40M -Xms40M -XX:NewRatio=3 -verbose:gc
    public static void main(String[] args) throws Exception {
        List<Object> list = new CopyOnWriteArrayList<Object>();
        list.add(new Struct());
        for (int i = 0; i < 1000; i++) {
            list.add(new byte[0]);
            Thread.sleep(100);
        }
    }


    private static class Struct {

        private byte[] bytes = new byte[1024 * 1024];

    }

}
