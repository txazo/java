package org.txazo.jdk8.time;

import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeTest {

    @Test
    public void test() {
        LocalTime time = LocalTime.now();
        System.out.println(time.format(DateTimeFormatter.ofPattern("HH:mm:ss:SSS")));
    }

}
