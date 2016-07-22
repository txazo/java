package org.txazo.java.reflection.type;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 参数化泛型
 */
public class ParameterizedTypeTest {

    private abstract class AbstractClass<K, V> {

    }

    private class MyClass extends AbstractClass<String, Integer> {

    }

    @Test
    public void test1() {
        Type type = MyClass.class.getGenericSuperclass();
        Assert.assertTrue(type instanceof ParameterizedType);
        ParameterizedType pType = (ParameterizedType) type;
        Assert.assertSame(String.class, pType.getActualTypeArguments()[0]);
        Assert.assertSame(Integer.class, pType.getActualTypeArguments()[1]);
        Assert.assertSame(ParameterizedTypeTest.class, pType.getOwnerType());
        Assert.assertSame(AbstractClass.class, pType.getRawType());
    }

}
