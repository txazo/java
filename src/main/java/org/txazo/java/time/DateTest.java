package org.txazo.java.time;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Date
 */
public class DateTest {

    @Test
    public void test() {
        /** Date实例化 */
        Date date1 = new Date();
        Date date2 = new Date(System.currentTimeMillis() + 10);

        /** Date比较 */
        Assert.assertTrue(date1.before(date2));
        Assert.assertTrue(date2.after(date1));
    }

}
