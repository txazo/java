package org.txazo.designpattern.creational.singleton;

/**
 * 静态内部类单例
 */
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton() {
    }

    public static StaticInnerClassSingleton getInstance() {
        return StaticInnerClassSingletonHolder.instance;
    }

    private static class StaticInnerClassSingletonHolder {

        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();

    }

}
