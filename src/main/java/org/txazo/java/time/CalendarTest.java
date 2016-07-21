package org.txazo.java.time;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Calendar
 */
public class CalendarTest {

    @Test
    public void test() {
        /** GregorianCalendar */
        Calendar c = Calendar.getInstance();

        System.out.println(c.get(Calendar.YEAR)); // 年
        System.out.println(c.get(Calendar.MONTH)); // 月
        System.out.println(c.get(Calendar.DAY_OF_YEAR)); // 日(年)
        System.out.println(c.get(Calendar.DAY_OF_MONTH)); // 日(月)
        System.out.println(c.get(Calendar.DAY_OF_WEEK)); // 日(星期)
        System.out.println(c.get(Calendar.WEEK_OF_YEAR)); // 星期(年)
        System.out.println(c.get(Calendar.WEEK_OF_MONTH)); // 星期(月)
        System.out.println(c.get(Calendar.HOUR)); // 小时(12时制)
        System.out.println(c.get(Calendar.HOUR_OF_DAY)); // 小时(24时制)
        System.out.println(c.get(Calendar.MINUTE)); // 分
        System.out.println(c.get(Calendar.SECOND)); // 秒
        System.out.println(c.get(Calendar.MILLISECOND)); // 毫秒

        // set
        c.set(Calendar.SECOND, 40);

        // add
        c.add(Calendar.HOUR_OF_DAY, 1);

        // Calendar转Date
        Date date = c.getTime();
    }

}
