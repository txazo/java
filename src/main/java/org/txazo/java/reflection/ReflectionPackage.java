package org.txazo.java.reflection;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.java.reflection.anno.PkgAnno;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

/**
 * 反射 - 包
 */
public class ReflectionPackage {

    @Test
    public void test1() {
        /** Package */
        Package pkg = Class.class.getPackage();
        Assert.assertEquals("java.lang", pkg.getName());
        Assert.assertEquals("Java Platform API Specification", pkg.getSpecificationTitle());
        Assert.assertEquals("Oracle Corporation", pkg.getSpecificationVendor());
        // assertEquals("1.7", pkg.getSpecificationVersion());
        Assert.assertEquals("Java Runtime Environment", pkg.getImplementationTitle());
        Assert.assertEquals("Oracle Corporation", pkg.getImplementationVendor());
        // assertEquals("1.7.0_75", pkg.getImplementationVersion());
    }

    @Test
    public void test2() {
        Package pkg = Package.getPackage("org.txazo.reflection");

        /** package-info包注解 */
        Assert.assertNotNull(pkg.getAnnotation(PkgAnno.class));

        /** package-info包类 */
        Assert.assertEquals(1, new PkgClass().pkg());

        /** package-info包常量 */
        Assert.assertEquals(1, PkgConstant.NUM);
    }

    @Test
    public void test3() throws IOException {
        /** Manifest */
        String classPath = this.getClass().getResource("/").getPath();
        String jarFile = classPath + "../txazo-java-reflection-1.0.jar";
        JarInputStream jis = new JarInputStream(new FileInputStream(jarFile), false);
        Manifest manifest = jis.getManifest();
        Attributes attr = manifest.getMainAttributes();
        Map.Entry entry = null;
        for (Iterator<Map.Entry<Object, Object>> i = attr.entrySet().iterator(); i.hasNext(); ) {
            entry = i.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        /** MANIFEST.MF读入  */
        manifest.read(new FileInputStream(this.getClass().getResource("/manifest.mf").getPath()));
        /** MANIFEST.MF写出 */
        manifest.write(new FileOutputStream(this.getClass().getResource("/manifest.mf").getPath()));
    }

    /**
     * MANIFEST.MF
     *
     * 1) Main-Class: org.txazo.reflection.Main
     * 2) Class-Path: lib/commons-lang3-3.4.jar
     */

}
