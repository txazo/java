package org.txazo.java.reflection.classloader.load;

import org.junit.Test;

import java.lang.reflect.Method;

public class ClassLoadTest {

    private static String CurrentClassName = "org.txazo.java.reflection.classloader.load.CurrentClass";
    private static String ParentClassName = "org.txazo.java.reflection.classloader.load.ParentClass";
    private static String InterfaceClassName = "org.txazo.java.reflection.classloader.load.InterfaceClass";
    private static String ReferenceClassName = "org.txazo.java.reflection.classloader.load.ReferenceClass";

    private void isClassLoaded(String name) throws Exception {
        Method method = ClassLoader.class.getDeclaredMethod("findLoadedClass", String.class);
        method.setAccessible(true);
        boolean loaded = method.invoke(ClassLoader.getSystemClassLoader(), name) != null;
        System.out.printf("%s Loaded: %s\n", name, loaded);
    }

    @Test
    public void test1() throws Exception {
        ClassLoader.getSystemClassLoader().loadClass(CurrentClassName);
        isClassLoaded(CurrentClassName);
        isClassLoaded(ParentClassName);
        isClassLoaded(InterfaceClassName);
        isClassLoaded(ReferenceClassName);
        String name = CurrentClass.CurrentClassName;
    }

    @Test
    public void test2() throws Exception {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Method method = classLoader.getClass().getDeclaredMethod("loadClass", String.class, boolean.class);
        method.setAccessible(true);
        method.invoke(classLoader, CurrentClassName, true);
        isClassLoaded(CurrentClassName);
        isClassLoaded(ParentClassName);
        isClassLoaded(InterfaceClassName);
        isClassLoaded(ReferenceClassName);
    }

    @Test
    public void test3() throws Exception {
        Class.forName("org.txazo.java.reflection.classloader.load.CurrentClass");
        isClassLoaded(CurrentClassName);
        isClassLoaded(ParentClassName);
        isClassLoaded(InterfaceClassName);
        isClassLoaded(ReferenceClassName);
        String name = InterfaceClass.InterfaceClassName;
    }

    @Test
    public void test4() throws Exception {
        Class.forName("org.txazo.java.reflection.classloader.load.CurrentClass", false, ClassLoader.getSystemClassLoader());
        isClassLoaded(CurrentClassName);
        isClassLoaded(ParentClassName);
        isClassLoaded(InterfaceClassName);
        isClassLoaded(ReferenceClassName);
    }

}
