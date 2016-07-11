package org.txazo.java.collection;

/**
 * fail-fast机制
 * <p>
 * 1. Collection/Map的一种错误检测机制
 *
 * @see java.util.ArrayList.Itr
 */
public class FailFastTest {

    /**
     * ArrayList
     *
     * 1. modCount: 标识修改的次数
     * 2. add()、remove(): modCount++
     * 3. set(): modCount不变
     */

    /**
     * ArrayList.Itr
     *
     * 1. 迭代器初始化: expectedModCount = modCount
     * 2. next()、remove(): 检查expectedModCount == modCount, 不相等抛出ConcurrentModificationException
     * 3. remove()操作后, 会重置expectedModCount = modCount
     * 4. remove()只能在next()之后调用, 且不能连续调用两次
     * 5. 迭代器迭代时, 调用ArrayList的add()、remove()操作, 抛出ConcurrentModificationException
     */

}
