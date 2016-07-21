package org.txazo.java.innerclass.staticinner;

public class OuterClass {

    private static Object staticField;

    private static Object getStaticField() {
        return staticField;
    }

    public static class InnerClass {

        public void inner() {
            staticField = getStaticField();
        }

    }

}
