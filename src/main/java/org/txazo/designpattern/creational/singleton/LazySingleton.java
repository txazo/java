package org.txazo.designpattern.creational.singleton;

/**
 * 懒汉式单例, 非线程安全
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

}
