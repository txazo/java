package org.txazo.java.collection.util;

import org.junit.Test;

import java.util.*;

/**
 * Collections
 *
 * @see Collections
 */
public class CollectionsTest {

    @Test
    public void test() {
        /** 空集合 */
        List list = Collections.EMPTY_LIST;
        Set set = Collections.EMPTY_SET;
        Map map = Collections.EMPTY_MAP;
        Collections.emptyList();
        Collections.emptySet();
        Collections.emptyMap();

        /** 不可变的只保护一个元素的集合 */
        Collections.singleton(new Object());
        Collections.singletonList(new Object());
        Collections.singletonMap(new Object(), new Object());

        /** List排序 */
        Collections.sort(new ArrayList<Comparable>());

        /** List乱序 */
        Collections.shuffle(new ArrayList<Object>());

        /** List反序 */
        Collections.reverse(new ArrayList<Object>());

        /** 数组添加到集合 */
        Collections.addAll(new ArrayList<Object>(), new Object[]{});
    }

    /**
     * Collections.synchronizedXXX()
     * <p>
     * 1) 返回同步的集合包装
     * 2) 装饰器模式
     * 3) 原理: final Object mutex, synchronized对象锁
     */
    @Test
    public void testSynchronized() {
        Collections.synchronizedList(new ArrayList<Object>());
        Collections.synchronizedSet(new HashSet<Object>());
        Collections.synchronizedMap(new HashMap<Object, Object>());
    }

    /**
     * Collections.unmodifiableXXX()
     * <p>
     * 1) 返回不可修改的集合包装, 只支持读, 修改时抛UnsupportedOperationException
     * 2) 装饰器模式
     */
    @Test
    public void testUnmodifiable() {
        Collections.unmodifiableList(new ArrayList<Object>());
        Collections.unmodifiableSet(new HashSet<Object>());
        Collections.unmodifiableMap(new HashMap<Object, Object>());
    }

}
