package org.txazo.java.lang;

import org.junit.Test;
import org.txazo.tool.util.EmptyUtils;

/**
 * 避免返回null
 * <p/>
 * 1) 数组 List Set Map: 返回空
 * 2) 对象: 返回空对象
 */
public class AvoidReturnNullTest {

    @Test(expected = UnsupportedOperationException.class)
    public void test1() {
        System.out.println(EmptyUtils.getEmptyArray(Integer.class).length);
        System.out.println(EmptyUtils.getEmptyList(Integer.class).size());
        System.out.println(EmptyUtils.getEmptySet(Integer.class).size());
        System.out.println(EmptyUtils.getEmptyMap(String.class, Integer.class).size());
        EmptyUtils.getEmptyObject(Object.class).hashCode();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test2() {
        Action.nullAction.doAction();
        Action.getNullAction().doAction();
        Action.errorAction.doAction();
    }

}

class Action {

    public void doAction() {
        System.out.println("doAction");
    }

    public static final Action nullAction = new Action() {

        @Override
        public void doAction() {
        }

    };

    public static final Action errorAction = new Action() {

        @Override
        public void doAction() {
            throw new UnsupportedOperationException("Action not found");
        }

    };

    public static Action getNullAction() {
        return new Action() {

            @Override
            public void doAction() {
            }

        };
    }

}
