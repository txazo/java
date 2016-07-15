package org.txazo.designpattern.behavior.interpreter;

/**
 * 非终结符表达式 - 加法
 */
public class PlusExpression extends DoubleExpression {

    public PlusExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public double interpreter() {
        return left.interpreter() + right.interpreter();
    }

}
