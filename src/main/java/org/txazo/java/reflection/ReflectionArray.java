package org.txazo.java.reflection;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.java.reflection.vo.Reflect;

import java.lang.reflect.Array;

/**
 * 反射 - 数组
 */
public class ReflectionArray {

    @Test
    public void test1() {
        /** 创建数组 */
        int[] intArray = (int[]) Array.newInstance(int.class, 1);
        intArray[0] = 1;

        /** 数组get */
        Assert.assertEquals(1, Array.get(intArray, 0));

        /** 数组set */
        Array.set(intArray, 0, 5);
        Assert.assertEquals(5, intArray[0]);
    }

    @Test
    public void test2() throws ClassNotFoundException {
        /** 获取数组class的三种方式 */
        Assert.assertSame(int[].class, Class.forName("[I"));
        Assert.assertSame(int[].class, Array.newInstance(int.class, 0).getClass());

        /** 数组的class name */
        Assert.assertEquals("[I", int[].class.getName());
        Assert.assertEquals("[Ljava.lang.String;", String[].class.getName());
        Assert.assertEquals("[Lorg.txazo.reflection.vo.Reflect;", Reflect[].class.getName());
    }

    @Test
    public void test3() {
        /** 数组的成员类型 */
        Class<?> intArrayClass = int[].class;
        Assert.assertEquals(int.class, intArrayClass.getComponentType());
    }

}
