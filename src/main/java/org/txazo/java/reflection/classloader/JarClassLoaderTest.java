package org.txazo.java.reflection.classloader;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.java.reflection.remote.api.VersionApi;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * JarClassLoaderTest
 */
public class JarClassLoaderTest {

    @Test
    public void test1() throws Exception {
        URL jar = new URL("file:txazo-reflection-remote-service.jar");
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{jar}, Thread.currentThread().getContextClassLoader());
        Class<VersionApi> versionApiClass = (Class<VersionApi>) urlClassLoader.loadClass("org.txazo.reflection.remote.service.VersionService");
        VersionApi versionApi = versionApiClass.newInstance();
        Assert.assertEquals("1.0", versionApi.getVersion());
    }

    @Test
    public void test2() throws Exception {
        URL jar = new URL("file:txazo-reflection-remote-service.jar");

        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{jar}, Thread.currentThread().getContextClassLoader());
        Class<?> versionApiClass = urlClassLoader.loadClass("org.txazo.reflection.remote.service.VersionService");
        Class<?> versionApiClass1 = urlClassLoader.loadClass("org.txazo.reflection.remote.service.VersionService");

        URLClassLoader urlClassLoader2 = new URLClassLoader(new URL[]{jar}, Thread.currentThread().getContextClassLoader());
        Class<?> versionApiClass2 = urlClassLoader2.loadClass("org.txazo.reflection.remote.service.VersionService");

        Assert.assertSame(versionApiClass, versionApiClass1);
        Assert.assertSame(versionApiClass, versionApiClass2);
    }

}
