package org.txazo.designpattern.behavior.strategy;

/**
 * 策略
 */
public class MulStrategy implements Strategy {

    @Override
    public int calculate(int a, int b) {
        return a * b;
    }

}
