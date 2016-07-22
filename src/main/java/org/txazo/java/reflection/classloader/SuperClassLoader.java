package org.txazo.java.reflection.classloader;

/**
 * SuperClassLoader
 */
public class SuperClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

}
