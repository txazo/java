package org.txazo.designpattern.behavior.interpreter;

/**
 * 非终结符表达式
 */
public abstract class DoubleExpression implements Expression {

    protected Expression left;
    protected Expression right;

    public DoubleExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

}
