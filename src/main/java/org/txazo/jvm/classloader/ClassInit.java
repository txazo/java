package org.txazo.jvm.classloader;

public class ClassInit extends ClassInitSuper {

    private static ClassInit instance = new ClassInit();

    private int status = 1;

    static {
        System.out.println("ClassInit 静态代码块");
    }

    public ClassInit() {
        super();
        System.out.println("ClassInit 构造函数");
    }

}
