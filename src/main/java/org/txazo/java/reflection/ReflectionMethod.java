package org.txazo.java.reflection;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.java.reflection.vo.Reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射 - 方法
 */
public class ReflectionMethod {

    @Test
    public void test1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<Reflect> clazz = Reflect.class;
        /** 获取所有public方法，包括继承自父类的方法 */
        Method[] methods = clazz.getMethods();
        Assert.assertEquals(5 + 9, methods.length);

        /** 根据方法名和参数类型获取方法 */
        Method method = clazz.getMethod("setId", int.class);
        /** 方法的参数类型 */
        Assert.assertSame(int.class, method.getParameterTypes()[0]);
        /** 方法的返回类型 */
        Assert.assertSame(void.class, method.getReturnType());

        Reflect reflect = new Reflect();
        /** 方法调用 */
        method.invoke(reflect, 5);
        Assert.assertEquals(5, reflect.getId());

        /** 私有方法调用 */
        method = clazz.getDeclaredMethod("privateMethod", null);
        method.setAccessible(true);
        method.invoke(reflect, null);

        /** 静态方法调用 */
        clazz.getMethod("setNUM", int.class).invoke(null, 10);
        Assert.assertEquals(10, Reflect.NUM);
    }

}
