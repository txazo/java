package org.txazo.jvm.classloader;

public class ClassInitSuper {

    private static ClassInitSuper instance = new ClassInitSuper();

    private int status = 1;

    static {
        System.out.println("ClassInitSuper 静态代码块");
    }

    public ClassInitSuper() {
        System.out.println("ClassInitSuper 构造函数");
    }

}
