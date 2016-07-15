package org.txazo.designpattern.behavior.strategy;

/**
 * 策略
 */
public class PlusStrategy implements Strategy {

    @Override
    public int calculate(int a, int b) {
        return a + b;
    }

}
