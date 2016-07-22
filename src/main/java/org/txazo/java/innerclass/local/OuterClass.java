package org.txazo.java.innerclass.local;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

// 外部类
public class OuterClass {

    // 实例变量
    private Object instanceField = new Object();
    // 静态变量
    private static Object staticField = new Object();

    // 实例方法
    private Object getInstanceField() {
        return instanceField;
    }

    // 静态方法
    private static Object getStaticField() {
        return staticField;
    }

    public void outerMethod() {
        // 方法的局部变量
        final Object localField = new Object();

        // 局部内部类
        class LocalInnerClass {

            public void innerMethod() {
                // 访问方法的局部变量
                localField.getClass();
                // 访问外部类的成员变量和静态变量
                instanceField = staticField;
                // 访问外部类的成员方法
                getInstanceField();
                // 访问外部类的静态方法
                getStaticField();
            }

        }

        // 实例化内部类
        new LocalInnerClass().innerMethod();
    }

    @Test
    public void test() throws ClassNotFoundException {
        Class<?> innerClass = Class.forName("org.txazo.java.innerclass.local.OuterClass$1LocalInnerClass");
        Assert.assertNotNull(innerClass);

        Field[] fields = innerClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("内部类变量: " + Modifier.toString(field.getModifiers()) + " " + field.getType().getName() + " " + field.getName());
        }

        Constructor[] constructors = innerClass.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("内部类构造方法: " + Modifier.toString(constructor.getModifiers()) + " " + constructor.getName() + Arrays.asList(constructor.getParameterTypes()).toString().replace("[", "(").replace("]", ")").replace("class ", ""));
        }

        Method[] methods = OuterClass.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("外部类方法: " + Modifier.toString(method.getModifiers()) + " " + method.getReturnType().getName() + " " + method.getName() + Arrays.asList(method.getParameterTypes()).toString().replace("[", "(").replace("]", ")").replace("class ", ""));
        }
    }

}
