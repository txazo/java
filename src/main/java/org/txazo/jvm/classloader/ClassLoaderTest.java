package org.txazo.jvm.classloader;

import org.junit.Assert;
import org.junit.Test;
import sun.misc.Launcher;

import java.net.URL;

/**
 * 类加载
 * <p>
 * <pre>
 * 启动类加载器
 *     |
 *     |
 * 扩展类加载器
 *     |
 *     |
 * 系统类加载器
 * </pre>
 */
public class ClassLoaderTest {

    @Test
    public void test() {
        /**
         * 系统类加载器
         *
         * 1) System ClassLoader
         * 2) java -classpath:
         * 3) java -Djava.class.path=
         */
        ClassLoader systemClassLoader = Launcher.getLauncher().getClassLoader();
        Assert.assertSame(systemClassLoader, this.getClass().getClassLoader());
        Assert.assertSame(systemClassLoader, ClassLoader.getSystemClassLoader());
        Assert.assertSame(systemClassLoader, Thread.currentThread().getContextClassLoader());
        System.out.println(System.getProperty("java.class.path"));
        Assert.assertEquals("sun.misc.Launcher$AppClassLoader", systemClassLoader.getClass().getName());

        /**
         * 扩展类加载器
         *
         * 1) Extension ClassLoader
         * 2) %JAVA_HOME%/jre/lib/ext
         * 3) java -Djava.ext.dirs=
         */
        ClassLoader extensionClassLoader = systemClassLoader.getParent();
        System.out.println(System.getProperty("java.ext.dirs"));
        Assert.assertEquals("sun.misc.Launcher$ExtClassLoader", extensionClassLoader.getClass().getName());

        /**
         * 启动类加载器
         *
         * 1) Bootstrap ClassLoader
         * 2) %JAVA_HOME%/jre/lib
         * 3) java -Xbootclasspath:
         * 4) java -Dsun.boot.class.path=
         */
        ClassLoader bootstrapClassLoader = extensionClassLoader.getParent();
        System.out.println(System.getProperty("sun.boot.class.path"));
        Assert.assertNull(bootstrapClassLoader);
        Assert.assertNull(System.class.getClassLoader());
        print(Launcher.getBootstrapClassPath().getURLs());
    }

    private static void print(URL[] urls) {
        if (urls != null && urls.length > 0) {
            for (int i = 0; i < urls.length; i++) {
                System.err.println(urls[i].getFile());
            }
        }
    }

}
