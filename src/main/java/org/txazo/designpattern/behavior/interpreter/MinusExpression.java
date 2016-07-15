package org.txazo.designpattern.behavior.interpreter;

/**
 * 非终结符表达式 - 减法
 */
public class MinusExpression extends DoubleExpression {

    public MinusExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public double interpreter() {
        return left.interpreter() - right.interpreter();
    }

}
