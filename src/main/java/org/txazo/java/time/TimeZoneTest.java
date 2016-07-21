package org.txazo.java.time;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * TimeZone
 * <p/>
 * 1) 时区
 */
public class TimeZoneTest {

    @Test
    public void test() {
        Calendar c = Calendar.getInstance();
        /** 默认时区 */
        c.setTimeZone(TimeZone.getDefault());
        System.out.println(DateFormatUtils.format(c, "yyyy-MM-dd HH:mm:ss"));
        c.setTimeZone(TimeZone.getTimeZone("Etc/GMT+8"));
        System.out.println(DateFormatUtils.format(c, "yyyy-MM-dd HH:mm:ss"));

        /** 所有时区 */
        for (String id : TimeZone.getAvailableIDs()) {
            System.out.println(id);
        }
    }

}
