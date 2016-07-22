package org.txazo.java.reflection.type;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;
import java.util.Map;

/**
 * 通配符泛型
 */
public class WildcardTypeTest {

    private class MyClass {

        private List<? extends Map> list;

    }

    @Test
    public void test1() throws NoSuchFieldException {
        Type type = MyClass.class.getDeclaredField("list").getGenericType();
        type = ((ParameterizedType) type).getActualTypeArguments()[0];
        Assert.assertTrue(type instanceof WildcardType);
        WildcardType wType = (WildcardType) type;
        Assert.assertSame(Map.class, wType.getUpperBounds()[0]);
    }

}
