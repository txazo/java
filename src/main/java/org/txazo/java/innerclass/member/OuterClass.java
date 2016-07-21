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
            // 访问外部类成员变量和静态变量
            field = staticField;
            // 访问外部类成员方法
            getField();
            // 访问外部类静态方法
            getStaticField();
        }

    }

//    static Object access$002(OutterClass outterClass, Object field) {
//        outterClass.field = field;
//        return field;
//    }
//
//    static Object access$100() {
//        return staticField;
//    }

}
