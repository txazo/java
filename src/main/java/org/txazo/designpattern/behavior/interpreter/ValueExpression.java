package org.txazo.designpattern.behavior.interpreter;

/**
 * 终结符表达式
 */
public class ValueExpression implements Expression {

    private double value;

    public ValueExpression(double value) {
        this.value = value;
    }

    @Override
    public double interpreter() {
        return value;
    }

}
