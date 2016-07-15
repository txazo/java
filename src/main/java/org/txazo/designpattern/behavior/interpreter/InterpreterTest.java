package org.txazo.designpattern.behavior.interpreter;

import org.junit.Assert;
import org.junit.Test;

/**
 * 解释器模式 - 四则运算
 */
public class InterpreterTest {

    @Test
    public void test() {
        Arithmetic arithmetic = new Arithmetic();
        Assert.assertEquals(9.0D, arithmetic.arithmetic("2 / 1 + 3 * 4 - 5"), 0.0001);
        Assert.assertEquals(0.6D, arithmetic.arithmetic("1 + 2 - 3 * 4 / 5"), 0.0001);
        Assert.assertEquals(0.6D, arithmetic.arithmetic("1 + 2 - (1 + 2) * (2 * 2) / 5"), 0.0001);
        Assert.assertEquals(17.0D, arithmetic.arithmetic("1 + (1 + 2 * (20 / 4 - 2) - 2) * 3 + 1"), 0.0001);
    }

}
