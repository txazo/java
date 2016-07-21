package org.txazo.java.innerclass;

public class OutterClass {

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
            field = staticField;
            getField();
            getStaticField();
        }

    }

}
