package org.txazo.designpattern.creational.builder;

/**
 * 抽象建造者
 */
public interface Builder {

    public void buildHead();

    public void buildBody();

    public void buildFoot();

    public Product build();

}
