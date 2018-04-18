package org.txazo.jdk8.time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {

    @Test
    public void test() {
        LocalDate date = LocalDate.now();
        System.out.println(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

}
