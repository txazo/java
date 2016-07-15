package org.txazo.designpattern.creational.singleton;

/**
 * 双检锁单例
 */
public class DoubleCheckLockingSingleton {

    private static final Object lock = new Object();
    private static volatile DoubleCheckLockingSingleton instance;

    private DoubleCheckLockingSingleton() {
    }

    public static DoubleCheckLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new DoubleCheckLockingSingleton();
                }
            }
        }
        return instance;
    }

}
