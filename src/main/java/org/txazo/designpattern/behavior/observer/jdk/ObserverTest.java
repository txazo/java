package org.txazo.designpattern.behavior.observer.jdk;

import org.junit.Test;

public class ObserverTest {

    @Test
    public void test() {
        NewsPaper paper = new NewsPaper();
        paper.addObserver(new Reader("张三"));
        paper.addObserver(new Reader("李四"));
        paper.addObserver(new Reader("王五"));
        paper.publish("人民日报发版了");
        paper.publish("法制周刊发版了");
        paper.publish("环球军事发版了");
    }

}
