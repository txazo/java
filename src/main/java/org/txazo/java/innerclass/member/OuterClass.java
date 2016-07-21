package org.txazo.java.innerclass.member;

public class OuterClass {

    private Object field;
    private static Object staticField;

    public Object getField() {
        return field;
    }

    public static Object getStaticField() {
        return staticField;
    }

    public class InnerClass {

        public void inner() {
            // 访问外部类的成员变量和静态变量
            field = staticField;
            // 访问外部类的成员方法
            getField();
            // 访问外部类的静态方法
            getStaticField();
        }

    }


}
