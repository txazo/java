package org.txazo.java.reflection;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.java.reflection.anno.FieldAnno;
import org.txazo.java.reflection.vo.Reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 反射 - 变量
 */
public class ReflectionField {

    @Test
    public void test1() throws Exception {
        /** 获取全部的Field */
        Field[] fields = Reflect.class.getDeclaredFields();
        Assert.assertEquals(3, fields.length);
    }

    @Test
    public void test2() throws NoSuchFieldException, IllegalAccessException {
        /** 根据名称查找Field */
        Field field = Reflect.class.getDeclaredField("id");
        Assert.assertNotNull(field);

        /** Field的修饰符 */
        Assert.assertTrue(Modifier.isPrivate(field.getModifiers()));

        /** Field的名称 */
        Assert.assertEquals("id", field.getName());

        /** Field的类型 */
        Assert.assertSame(int.class, field.getType());

        /** Field的注解 */
        Assert.assertEquals("id", field.getAnnotation(FieldAnno.class).desc());

        /** Field的get/set */
        Reflect reflect = new Reflect(1, "txazo");
        /** 访问private的Field */
        field.setAccessible(true);
        Assert.assertEquals(1, field.get(reflect));
        field.set(reflect, 5);
        Assert.assertEquals(5, reflect.getId());
    }

    @Test
    public void test3() throws NoSuchFieldException, IllegalAccessException {
        /** 静态的Field */
        Reflect.NUM = 1;
        Field field = Reflect.class.getDeclaredField("NUM");
        Assert.assertEquals(1, field.get(null));
        field.set(null, 5);
        Assert.assertEquals(5, Reflect.NUM);
    }

}
