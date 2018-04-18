package org.txazo.jdk8.time;

import org.junit.Test;

import java.time.Clock;
import java.time.format.DateTimeFormatter;

public class ClockTest {

    @Test
    public void test() {
        Clock clock = Clock.systemUTC();
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(clock.instant()));
        System.out.printf("Current: %d\n", clock.millis());
    }

}
