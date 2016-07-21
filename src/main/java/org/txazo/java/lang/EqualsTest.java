package org.txazo.java.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * Object.equals()
 * <p/>
 * 1) ==比较引用是否相等
 */
public class EqualsTest {

    @Test
    public void test() {
        NotOverrideEquals notOverrideEquals = new NotOverrideEquals();
        Assert.assertEquals(notOverrideEquals, notOverrideEquals);
        Assert.assertNotEquals(notOverrideEquals, new NotOverrideEquals());
        Assert.assertEquals(new OverrideEquals(), new OverrideEquals());
        Assert.assertEquals(new OverrideEquals("cat"), new OverrideEquals("cat"));
        Assert.assertNotEquals(new OverrideEquals("cat"), new OverrideEquals("get"));
    }

    /**
     * 未重写equals(), 比较引用是否相等
     */
    private class NotOverrideEquals {

    }

    /**
     * 重写equals()
     */
    private class OverrideEquals {

        private String name;

        public OverrideEquals() {
        }

        public OverrideEquals(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            OverrideEquals that = (OverrideEquals) o;
            return name != null ? name.equals(that.name) : that.name == null;
        }

    }

}
