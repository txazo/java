package org.txazo.designpattern.behavior.strategy;

/**
 * 上下文环境
 */
public class Context {

    // 策略
    private Strategy strategy;

    public int calculate(int a, int b) {
        return strategy.calculate(a, b);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

}
