package org.txazo.java.lang;

/**
 * ==号
 *
 * ==号始终是比较栈中变量的值, 即基本类型的值或对象的引用
 */
public class EqualSignTest {

    /**
     * 比较int值
     */
    public void test1() {
        int a = 1;
        int b = 1;
        boolean result = a == b;
    }

    /**
     * 拆箱后, 比较int值
     */
    public void test2() {
        int a = 1;
        Integer b = 1;
        boolean result = a == b;
    }

    /**
     * 比较对象引用地址
     */
    public void test3() {
        Integer a = 1;
        Integer b = 1;
        boolean result = a == b;
    }

}
