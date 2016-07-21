package org.txazo.java.collection.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList - 数组实现的有序列表
 * <p>
 * 1) 线程不安全
 * 2) 实现: Object[] elementData, int size
 * 3) 大小可动态增长, 默认大小10, 每次扩容1/2, 可指定初始大小, 尽量避免扩容
 * 4) 基于数组实现: 在内存中为一块连续的内存空间
 */
public class ArrayListTest {

    @Test
    public void test() {
        /** 指定初始大小 */
        List<Integer> list = new ArrayList<Integer>(20);

        /** ArrayList.subList()内部实现: 复用父List的elementData */
        List<Integer> subList = new ArrayList<Integer>(list.subList(1, 5));

        /** ArrayList转为数组 */
        Integer[] intArray = list.toArray(new Integer[0]);
    }

}
