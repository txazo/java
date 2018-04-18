package org.txazo.java.reflection.classloader.load;

public interface InterfaceClass {

    public static final String InterfaceClassName = sout("InterfaceClass static field");

    static String sout(String str) {
        System.out.println(str);
        return str;
    }

}
