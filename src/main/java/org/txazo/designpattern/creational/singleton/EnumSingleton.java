package org.txazo.designpattern.creational.singleton;

/**
 * 枚举单例
 */
public enum EnumSingleton {

    INSTANCE;

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

}
