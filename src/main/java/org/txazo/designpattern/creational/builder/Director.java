package org.txazo.designpattern.creational.builder;

/**
 * 指导者
 */
public class Director {

    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Product getProduct() {
        builder.buildHead();
        builder.buildBody();
        builder.buildFoot();
        return builder.build();
    }

}
