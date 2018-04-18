package org.txazo.java.reflection.classloader.load;

public class ReferenceClass {

    private static String parentName = sout("ReferenceClass static field");

    static {
        sout("ReferenceClass static block");
    }

    public static String sout(String str) {
        System.out.println(str);
        return str;
    }

}
