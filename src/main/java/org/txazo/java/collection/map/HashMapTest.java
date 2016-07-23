package org.txazo.java.collection.map;

/**
 * HashMap
 * <p>
 * 1) 非线程安全, key和value都可为null
 * 2) 拉链法: Entry数组 + Entry链表
 * 3) Entry: hash key value next
 * 4) 原理: key - hash() - hash - index - Entry[index] - Entry - Entry.key - (key == Entry.key || key.equals(Entry.key))
 * 5) 容量: 容量为2的n次幂, 默认容量16, 容量超过capacity * loadFactor时扩容一倍, 可设置初始容量, 避免频繁扩容
 * 6) rehash: 扩容时, 重建Entry数组
 * 7) fail-fast机制
 * 8) key不要为可变对象
 */
public class HashMapTest {

}
