package org.txazo.java.reflection;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.java.reflection.vo.Reflect;

import java.lang.reflect.Constructor;

/**
 * 反射 - 构造方法
 */
public class ReflectionConstructor {

    @Test
    public void test1() throws Exception {
        Class<Reflect> clazz = Reflect.class;

        /** 获取Constructor */
        Constructor<Reflect> constructor = clazz.getConstructor();
        Assert.assertNotNull(constructor);

        /** Constructor实例化类 */
        Reflect reflect = constructor.newInstance();
        Assert.assertNotNull(reflect);

        constructor = clazz.getDeclaredConstructor(int.class, String.class);
        Assert.assertNotNull(constructor);

        /** 构造方法参数 */
        Assert.assertSame(int.class, constructor.getParameterTypes()[0]);
        Assert.assertSame(String.class, constructor.getParameterTypes()[1]);
        reflect = constructor.newInstance(5, "txazo");
        Assert.assertEquals(5, reflect.getId());

        /** 获取所有的public Constructor */
        Constructor<?>[] constructors = clazz.getConstructors();
        Assert.assertEquals(2, constructors.length);
    }

}
