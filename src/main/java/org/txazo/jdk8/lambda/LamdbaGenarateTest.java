package org.txazo.jdk8.lambda;

public class LamdbaGenarateTest {

    public static void main(String[] args) {
        // 导出生成的内部类到指定目录
        System.setProperty("jdk.internal.lambda.dumpProxyClasses", "/Users/txazo/TxazoProject/java/target/classes");
        // Lamdba表达式
        Calculater left = (a, b) -> a;
        left.calculate(1, 2);
        // 方法引用
        Calculater right = LamdbaGenarateTest::right;
        right.calculate(1, 2);
    }

    private static int right(int a, int b) {
        return b;
    }

    @FunctionalInterface
    private interface Calculater {

        int calculate(int a, int b);

    }

}
