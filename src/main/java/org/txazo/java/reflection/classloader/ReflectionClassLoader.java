package org.txazo.java.reflection.classloader;

/**
 * ReflectionClassLoader
 */
class ReflectionClassLoader extends ClassLoader {

    public ReflectionClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

}
