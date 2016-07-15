package org.txazo.designpattern.behavior.template.core;

/**
 * 抽象模板
 */
public abstract class AbstractTemplate {

    /**
     * 模板方法，固定算法骨架
     */
    public final void templateMethod() {
        concreteMethod();
        abstractMethod();
        hookMethod();
    }

    /**
     * 公共方法，父类实现
     */
    private void concreteMethod() {
        System.out.println("AbstractTemplate concreteMethod");
    }

    /**
     * 抽象方法，延迟子类实现
     */
    protected abstract void abstractMethod();

    /**
     * 钩子方法，提供默认实现
     */
    protected void hookMethod() {
        System.out.println("AbstractTemplate hookMethod");
    }

}
