/**
 * 原子操作类
 * <p>
 * 1) 基本类型和引用类型: AtomicBoolean, AtomicInteger, AtomicLong, AtomicReference
 * 2) 对象字段类型: AtomicIntegerFieldUpdater, AtomicLongFieldUpdater, AtomicReferenceFieldUpdater
 * 3) 数组类型: AtomicIntegerArray, AtomicLongArray, AtomicReferenceArray
 * 4) 实现原理: Unsafe, 获取内存偏移, 传入对象和内存偏移进行操作
 * <p>
 */
package org.txazo.java.concurrency.atomic;
