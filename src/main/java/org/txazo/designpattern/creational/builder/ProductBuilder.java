package org.txazo.designpattern.creational.builder;

/**
 * 产品建造者
 */
public class ProductBuilder implements Builder {

    private Product product;

    public ProductBuilder() {
        product = new Product();
    }

    @Override
    public void buildHead() {
        product.setHead("head");
    }

    @Override
    public void buildBody() {
        product.setBody("body");
    }

    @Override
    public void buildFoot() {
        product.setFoot("foot");
    }

    @Override
    public Product build() {
        return product;
    }

}
