package org.txazo.designpattern.creational.builder;

import org.junit.Assert;
import org.junit.Test;

/**
 * 建造者模式
 */
public class BuilderTest {

    @Test
    public void test() {
        Builder builder = new ProductBuilder();
        Director director = new Director(builder);
        Product product = director.getProduct();
        Assert.assertEquals(product.getHead(), "head");
        Assert.assertEquals(product.getBody(), "body");
        Assert.assertEquals(product.getFoot(), "foot");
    }

}
