package org.txazo.designpattern.creational.singleton;

import org.junit.Assert;
import org.junit.Test;

/**
 * 单例模式
 */
public class SingletonTest {

    @Test
    public void testLazySingleton() {
        Assert.assertSame(LazySingleton.getInstance(), LazySingleton.getInstance());
    }

    @Test
    public void testHungrySingleton() {
        Assert.assertSame(HungrySingleton.getInstance(), HungrySingleton.getInstance());
    }

    public void testDoubleCheckLockingSingleton() {
        Assert.assertSame(DoubleCheckLockingSingleton.getInstance(), DoubleCheckLockingSingleton.getInstance());
    }

    @Test
    public void testStaticInnerClassSingleton() {
        Assert.assertSame(StaticInnerClassSingleton.getInstance(), StaticInnerClassSingleton.getInstance());
    }

    @Test
    public void testEnumSingleton() {
        Assert.assertSame(EnumSingleton.getInstance(), EnumSingleton.getInstance());
    }

}
