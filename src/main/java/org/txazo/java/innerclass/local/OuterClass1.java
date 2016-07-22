//package org.txazo.java.innerclass.local;
//
//public class OuterClass1 {
//
//    private Object instanceField = new Object();
//    private static Object staticField = new Object();
//
//    private Object getInstanceField() {
//        return instanceField;
//    }
//
//    private static Object getStaticField() {
//        return staticField;
//    }
//
//    public void outerMethod() {
//        final Object localField = new Object();
//        new OuterClass$1LocalInnerClass(this, localField).innerMethod();
//    }
//
//    static Object access$002(OuterClass outerClass, Object instanceField) {
//        outerClass.instanceField = instanceField;
//        return instanceField;
//    }
//
//    static Object access$100() {
//        return staticField;
//    }
//
//    static Object access$200(OuterClass outerClass) {
//        return outerClass.getInstanceField();
//    }
//
//    static Object access$300() {
//        return getStaticField();
//    }
//
//}
//
//class OuterClass$1LocalInnerClass {
//
//    final OuterClass this$0;
//    final Object val$localField;
//
//    public OuterClass$1LocalInnerClass(OuterClass this$0, Object val$localField) {
//        this.this$0 = this$0;
//        this.val$localField = val$localField;
//    }
//
//    public void outerMethod() {
//        this.val$localField.getClass();
//        OuterClass.access$002(this.this$0, OuterClass.access$100());
//        OuterClass.access$200(this.this$0);
//        OuterClass.access$300();
//    }
//
//}
