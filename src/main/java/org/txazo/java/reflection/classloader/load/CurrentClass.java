package org.txazo.java.reflection.classloader.load;

public class CurrentClass extends ParentClass implements InterfaceClass {

    public static String CurrentClassName = sout("CurrentClass static field");

    // private static ReferenceClass reference = null;
    private static ReferenceClass reference = new ReferenceClass();

    static {
        sout("CurrentClass static block");
    }

}
