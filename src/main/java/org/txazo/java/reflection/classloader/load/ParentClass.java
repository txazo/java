package org.txazo.java.reflection.classloader.load;

public class ParentClass {

    public static String ParentClassName = sout("ParentClass static field");

    static {
        sout("ParentClass static block");
    }

    protected static String sout(String str) {
        System.out.println(str);
        return str;
    }

}
