package org.txazo.java.reflection.javassist;

import javassist.*;
import org.junit.Test;

import java.lang.reflect.Field;

public class JavassistTest {

    private ClassPool pool = ClassPool.getDefault();

    @Test
    public void testCtClass() throws Exception {
        CtClass cc = pool.get("org.txazo.reflection.javassist.Javassist");
        System.out.println(cc.getName());
        System.out.println(cc.toClass().getName());
    }

    @Test
    public void testNewCtClass() throws Exception {
        // 生成类
        CtClass newCc = pool.makeClass("org.txazo.reflection.javassist.JavassistNew");
        System.out.println(newCc.getName());
    }

    @Test
    public void testNewCtMethod() throws Exception {
        CtClass cc = pool.get("org.txazo.reflection.javassist.Javassist");

        // 生成方法
        CtMethod cm = CtNewMethod.make("public static int add(int x, int y) { return x + y; }", cc);
        cc.addMethod(cm);

        cc.writeFile();

        System.out.println(cc.toClass().getDeclaredMethod("add", new Class[]{int.class, int.class}).invoke(null, 1, 2));
    }

    @Test
    public void testModifyCtMethod() throws Exception {
        CtClass cc = pool.get("org.txazo.reflection.javassist.Javassist");

        // 生成方法
        CtMethod cm = cc.getDeclaredMethod("multi");
        cm.insertBefore("System.out.println(\"a = \" + $1); System.out.println(\"b = \" + $2);");

        cc.writeFile();

        System.out.println(cc.toClass().getDeclaredMethod("multi", new Class[]{int.class, int.class}).invoke(null, 2, 3));
    }

    @Test
    public void testNewCtField() throws Exception {
        CtClass cc = pool.get("org.txazo.reflection.javassist.Javassist");

        // 生成变量
        CtField cf = new CtField(CtClass.intType, "id", cc);
        cc.addField(cf, "1");

        cc.writeFile();

        Class<?> newClass = cc.toClass();
        Field field = newClass.getDeclaredField("id");
        field.setAccessible(true);
        System.out.println(field.get(newClass.newInstance()));
    }

}
