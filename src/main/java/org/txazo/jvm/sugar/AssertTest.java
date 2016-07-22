package org.txazo.jvm.sugar;

public class AssertTest {

    public static void testAssert(boolean condition, Object message) {
        assert condition : message;
    }

//    static final boolean $assertionsDisabled;
//
//    static {
//        $assertionsDisabled = !AssertTest.class.desiredAssertionStatus() ? true : false;
//    }
//
//    public void testAssert(boolean condition, Object message) {
//        if (!$assertionsDisabled) {
//            if (!condition) {
//                throw new AssertionError(message);
//            }
//        }
//    }

}
