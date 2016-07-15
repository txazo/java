package org.txazo.designpattern.behavior.interpreter;

/**
 * 非终结符表达式 - 乘法
 */
public class MulExpression extends DoubleExpression {

    public MulExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public double interpreter() {
        return left.interpreter() * right.interpreter();
    }

}
