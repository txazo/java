package org.txazo.jvm.sugar;

/**
 * 可变长参数 - 语法糖
 * <p>
 * 1) 在编译阶段, 可变长参数被编译为数组
 * 2) 可变长参数必须是方法参数的最后一项
 * 3) 可变长参数和数组是同一种方法参数类型
 */
public class VariableParameterSugar {

    public void method(String... args) {

    }

    public void methodDeCompile(String[] args) {

    }

}
