package org.txazo.java.reflection.type;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * 数组泛型
 */
public class GenericArrayTypeTest {

    private interface MyInterface<T> {

    }

    private class MyClass<T> implements MyInterface<T> {

        private T[] array;

        public T[] getArray() {
            return array;
        }

        public void setArray(T[] array) {
            this.array = array;
        }
    }

    @Test
    public void test1() throws NoSuchFieldException {
        Type type = MyClass.class.getDeclaredField("array").getGenericType();
        Assert.assertTrue(type instanceof GenericArrayType);
        GenericArrayType arrayType = (GenericArrayType) type;
        Type cType = arrayType.getGenericComponentType();
        Assert.assertTrue(cType instanceof TypeVariable);
        TypeVariable vType = (TypeVariable) cType;
        Assert.assertEquals("T", vType.getName());
        /** 默认T extends Object */
        Assert.assertSame(Object.class, vType.getBounds()[0]);
        Assert.assertSame(MyClass.class, vType.getGenericDeclaration());
    }

}
