package org.txazo.java.reflection;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;
import org.txazo.java.reflection.anno.ClassAnno;
import org.txazo.java.reflection.vo.Reflect;
import org.txazo.java.reflection.vo.ReflectInterface;
import org.txazo.java.reflection.vo.SuperInterface;
import org.txazo.java.reflection.vo.SuperReflect;

import java.lang.reflect.Modifier;
import java.util.AbstractList;
import java.util.ArrayList;

/**
 * 反射 - 类
 */
public class ReflectionClass {

    private class InnerClass {

    }

    @Test
    public void test1() throws ClassNotFoundException, NoSuchFieldException {
        /** class对象的获取 */

        /** 对象、原始数据类型、void、Void */
        Assert.assertNotNull(Reflect.class);
        /** 原始数据类型的包装类 Void */
        Assert.assertSame(int.class, Integer.TYPE);
        /** 对象的getClass() */
        Assert.assertSame(Reflect.class, new Reflect().getClass());
        /** Class.forName() */
        Assert.assertSame(Reflect.class, Class.forName("org.txazo.reflection.vo.Reflect"));
        /** ClassLoader的loadClass() */
        Assert.assertSame(Reflect.class, Thread.currentThread().getContextClassLoader().loadClass("org.txazo.reflection.vo.Reflect"));
        /** 父类class */
        Assert.assertSame(AbstractList.class, ArrayList.class.getSuperclass());
        /** 外部类class */
        Assert.assertSame(ReflectionClass.class, InnerClass.class.getEnclosingClass());
        /** 内部类class(public、递归继承) */
        Assert.assertTrue(ArrayUtils.isEmpty(ReflectionClass.class.getClasses()));
        /** 内部类class(private、不可继承) */
        Assert.assertSame(InnerClass.class, ReflectionClass.class.getDeclaredClasses()[0]);
    }

    @Test
    public void test2() {
        Class<?> clazz = Reflect.class;
        /** 包名 */
        Assert.assertEquals("org.txazo.reflection.vo", clazz.getPackage().getName());
        /** 类注解 */
        Assert.assertTrue(clazz.isAnnotationPresent(ClassAnno.class));
        /** 修饰符 */
        Assert.assertTrue(Modifier.isPublic(clazz.getModifiers()));
        /** 类名 */
        Assert.assertEquals("Reflect", clazz.getSimpleName());
        /** 全限定类名 */
        Assert.assertEquals("org.txazo.reflection.vo.Reflect", clazz.getName());
        /** 父类 */
        Assert.assertSame(SuperReflect.class, clazz.getSuperclass());
        Assert.assertSame(Object.class, clazz.getSuperclass().getSuperclass());
        /** 接口 */
        Assert.assertSame(ReflectInterface.class, clazz.getInterfaces()[0]);
        Assert.assertSame(SuperInterface.class, clazz.getInterfaces()[0].getInterfaces()[0]);
        Assert.assertTrue(ArrayUtils.isEmpty(clazz.getInterfaces()[0].getInterfaces()[0].getInterfaces()));
    }

    @Test
    public void test3() {
        Class<?> clazz = this.getClass();
        /** 当前目录路径 */
        Assert.assertTrue(clazz.getResource("").getPath().endsWith("org/txazo/reflection/"));
        /** 当前目录路径 */
        Assert.assertTrue(clazz.getResource(".").getPath().endsWith("org/txazo/reflection/"));
        /** 根目录路径 */
        Assert.assertTrue(clazz.getResource("/").getPath().endsWith("classes/"));

        /** 读取当前目录下文件 */
        Assert.assertNotNull(clazz.getResourceAsStream("package-info.class"));
        /** 读取根目录下文件 */
        Assert.assertNotNull(clazz.getResourceAsStream("/reflect.properties"));
    }

    @Test
    public void test4() throws ClassNotFoundException {
        /** asSubclass */
        Class<? extends SuperReflect> clazz = Class.forName("org.txazo.reflection.vo.Reflect").asSubclass(SuperReflect.class);
        Assert.assertNotNull(clazz);
    }

    @Test
    public void test5() {
        /** getName、getSimpleName、getCanonicalName */
        assertClassName(int.class, "int", "int", "int");
        assertClassName(int[].class, "[I", "int[]", "int[]");
        assertClassName(int[][].class, "[[I", "int[][]", "int[][]");
        assertClassName(Reflect.class, "org.txazo.reflection.vo.Reflect", "Reflect", "org.txazo.reflection.vo.Reflect");
        assertClassName(Reflect[].class, "[Lorg.txazo.reflection.vo.Reflect;", "Reflect[]", "org.txazo.reflection.vo.Reflect[]");
        assertClassName(Reflect[][].class, "[[Lorg.txazo.reflection.vo.Reflect;", "Reflect[][]", "org.txazo.reflection.vo.Reflect[][]");
    }

    private void assertClassName(Class<?> clazz, String name, String simpleName, String canonicalName) {
        Assert.assertEquals(name, clazz.getName());
        Assert.assertEquals(simpleName, clazz.getSimpleName());
        Assert.assertEquals(canonicalName, clazz.getCanonicalName());
    }

}
