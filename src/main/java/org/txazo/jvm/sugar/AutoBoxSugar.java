package org.txazo.jvm.sugar;

import org.junit.Assert;
import org.junit.Test;

/**
 * 自动装箱拆箱 - 语法糖
 * <p>
 * 1) 装箱: 基本数据类型转换为包装类型
 * 2) 拆箱: 包装类型转换为基本数据类型
 */
public class AutoBoxSugar {

    @Test
    public void test() {
        Integer a = 10;
        // 拆箱
        int b = a;
        // 拆箱
        Assert.assertTrue(a == b);
        // 装箱
        Assert.assertTrue(a.equals(b));
    }

    @Test
    public void testDecompile() {
        Integer a = Integer.valueOf(10);
        int b = a.intValue();
        Assert.assertTrue(a.intValue() == b);
        Assert.assertTrue(a.equals(Integer.valueOf(b)));
    }

}
