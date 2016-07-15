package org.txazo.designpattern.behavior.interpreter;

/**
 * 非终结符表达式 - 除法
 */
public class SubExpression extends DoubleExpression {

    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public double interpreter() {
        return left.interpreter() / right.interpreter();
    }

}
