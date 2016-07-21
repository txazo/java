package org.txazo.java.innerclass.staticinner;

public class OuterClass {

    private static Object staticField;

    private static Object getStaticField() {
        return staticField;
    }

    public static class InnerClass {

        public void inner() {
            // 访问外部类的静态变量和静态方法
            staticField = getStaticField();
        }

    }

    // 提供给内部类访问静态变量
//    static Object access$002(Object staticField) {
//        OutterClass.staticField = staticField;
//        return staticField;
//    }

    // 提供给内部类访问静态方法
//    static Object access$100() {
//        return getStaticField();
//    }

}
