package org.txazo.java.innerclass.local;

// 外部类
public class OuterClass {

    // 实例变量
    private Object instanceField;
    // 静态变量
    private static Object staticField;

    // 实例方法
    private Object getInstanceField() {
        return instanceField;
    }

    // 静态方法
    private static Object getStaticField() {
        return staticField;
    }

    public void outerMethod() {
        // 方法的局部变量
        final Object localField = new Object();

        // 局部内部类
        class LocalInnerClass {

            public void innerMethod() {
                // 访问方法的局部变量
                localField.getClass();
                // 访问外部类的成员变量
                instanceField.getClass();
                // 访问外部类的静态变量
                staticField.getClass();
                // 访问外部类的成员方法
                getInstanceField();
                // 访问外部类的静态方法
                getStaticField();
            }

        }
    }

}
