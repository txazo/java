package org.txazo.java.innerclass.anoymous;

public class OuterClass {

    // 实例变量
    private Object field;
    // 静态变量
    private static Object staticField;

    // 实例方法
    public Object getField() {
        return field;
    }

    // 静态方法
    public static Object getStaticField() {
        return staticField;
    }

    public void outter() {
        final Object localField = new Object();

        new InnerClass() {

            @Override
            public void inner() {
                field = localField;
                // 访问外部类成员变量和静态变量
                field = staticField;
                // 访问外部类成员方法
                getField();
                // 访问外部类静态方法
                getStaticField();
            }

        }.inner();
    }

}

interface InnerClass {

    public void inner();

}
