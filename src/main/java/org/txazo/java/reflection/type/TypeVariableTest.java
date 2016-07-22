package org.txazo.java.reflection.type;

import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

/**
 * 泛型参数
 */
public class TypeVariableTest {

    private interface MyInterface<T extends List & Serializable, K> {

    }

    private class MyClass<T extends List & Serializable, K> implements MyInterface<T, K> {

        private T t;

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

    }

    private void assertT(TypeVariable t) {
        /** 泛型参数名称 */
        Assert.assertEquals("T", t.getName());
        /** 泛型的父类型  */
        Assert.assertSame(List.class, t.getBounds()[0]);
        Assert.assertSame(Serializable.class, t.getBounds()[1]);
        /** 泛型声明的类 */
        Assert.assertSame(MyClass.class, t.getGenericDeclaration());
    }

    @Test
    public void test1() {
        TypeVariable[] typeVariables = MyClass.class.getTypeParameters();
        Assert.assertEquals(2, typeVariables.length);
        assertT(typeVariables[0]);
    }

    @Test
    public void test2() throws NoSuchFieldException {
        Type type = MyClass.class.getDeclaredField("t").getGenericType();
        Assert.assertTrue(type instanceof TypeVariable);
        assertT((TypeVariable) type);
    }

    @Test
    public void test3() throws NoSuchMethodException {
        Type type = MyClass.class.getMethod("getT").getGenericReturnType();
        Assert.assertTrue(type instanceof TypeVariable);
        assertT((TypeVariable) type);
    }

    @Test
    public void test4() throws NoSuchMethodException {
        Type[] types = MyClass.class.getMethod("setT", List.class).getGenericParameterTypes();
        Assert.assertTrue(types[0] instanceof TypeVariable);
        assertT((TypeVariable) types[0]);
    }

}
