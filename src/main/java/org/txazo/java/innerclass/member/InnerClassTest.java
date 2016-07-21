package org.txazo.java.innerclass.member;

public class InnerClassTest {

    public static void main(String[] args) {
        OuterClass outterClass = new OuterClass();
        OuterClass.InnerClass innerClass = outterClass.new InnerClass();
    }

}
