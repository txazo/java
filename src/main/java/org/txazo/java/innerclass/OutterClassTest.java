package org.txazo.java.innerclass;

public class OutterClassTest {

    public static void main(String[] args) {
        OutterClass outterClass = new OutterClass();
        OutterClass.InnerClass innerClass = outterClass.new InnerClass();
    }

}
