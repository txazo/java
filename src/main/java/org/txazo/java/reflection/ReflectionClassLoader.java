package org.txazo.java.reflection;

import org.junit.Assert;
import org.junit.Test;

/**
 * 反射 - 类加载器
 */
public class ReflectionClassLoader {

    @Test
    public void test1() {
        /** System ClassLoader: classpath, java.class.path */
        ClassLoader application = this.getClass().getClassLoader();
        /** Extension ClassLoader: %JAVA_HOME%/lib/ext, java.ext.dirs */
        ClassLoader extension = application.getParent();
        /** Bootstrap ClassLoader: %JAVA_HOME%/lib, sun.boot.class.path */
        ClassLoader system = extension.getParent();
        Assert.assertEquals("sun.misc.Launcher$AppClassLoader", application.getClass().getName());
        Assert.assertEquals("sun.misc.Launcher$ExtClassLoader", extension.getClass().getName());
        Assert.assertNull(system);
    }

    @Test
    public void test2() throws ClassNotFoundException {
        Class<?> clazz = this.getClass().getClassLoader().loadClass("org.txazo.reflection.vo.Reflect");
        Assert.assertNotNull(clazz);
    }

    private class MyClassLoader extends ClassLoader {

        public MyClassLoader(ClassLoader parent) {
            super(parent);
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            return super.loadClass(name);
        }

    }

}
