package org.txazo.java.reflection;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.java.reflection.anno.*;
import org.txazo.java.reflection.vo.AnnoClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 反射 - 注解
 */
public class ReflectionAnnotation {

    private Class<AnnoClass> clazz = AnnoClass.class;

    @Test
    public void test1() {
        /** 包注解 */
        Assert.assertNotNull(Package.getPackage("org.txazo.reflection").getAnnotation(PkgAnno.class));
    }

    @Test
    public void test2() {
        /** 类注解 */
        Assert.assertNotNull(clazz.getAnnotation(ClassAnno.class));
    }

    @Test
    public void test3() throws NoSuchMethodException {
        /** 构造方法注解 */
        Assert.assertNotNull(clazz.getConstructor().getAnnotation(ConstructorAnno.class));
    }

    @Test
    public void test4() throws NoSuchFieldException {
        /** 变量注解 */
        Assert.assertNotNull(clazz.getDeclaredField("id").getAnnotation(FieldAnno.class));
    }

    @Test
    public void test5() throws NoSuchMethodException {
        /** 方法注解 */
        Assert.assertNotNull(clazz.getDeclaredMethod("setId", int.class).getAnnotation(MethodAnno.class));
    }

    @Test
    public void test6() throws NoSuchMethodException {
        /** 方法参数注解 */
        Method method = clazz.getDeclaredMethod("param", int.class, String.class);
        Annotation[][] annotations = method.getParameterAnnotations();
        Assert.assertEquals("id", ((ParamAnno) annotations[0][0]).name());
        Assert.assertEquals("name", ((ParamAnno) annotations[1][0]).name());
    }

}
