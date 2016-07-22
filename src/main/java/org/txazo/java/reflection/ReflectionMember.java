package org.txazo.java.reflection;

import org.junit.Test;
import org.txazo.java.reflection.vo.Reflect;

public class ReflectionMember {

    @Test
    public void test1() throws Exception {
        Class<?> clazz = Reflect.class;

        /** public、递归继承(Constructor除外) */
        clazz.getClasses();
        clazz.getField("NUM");
        clazz.getFields();
        clazz.getConstructor();
        clazz.getConstructors();
        clazz.getMethod("getName");
        clazz.getMethods();

        /** private、不可继承 */
        clazz.getDeclaredClasses();
        clazz.getDeclaredField("id");
        clazz.getDeclaredFields();
        clazz.getDeclaredConstructor();
        clazz.getDeclaredConstructors();
        clazz.getDeclaredMethod("privateMethod");
        clazz.getDeclaredMethods();
    }

}
