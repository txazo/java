package org.txazo.designpattern.behavior.command.callback;

import org.junit.Test;

public class CommandTest {

    @Test
    public void test() {
        final TV tv = new TV();
        Control control = new Control();
        control.request(new Command() {

            @Override
            public void execute() {
                tv.turnOn();
            }

        });
        control.request(new Command() {

            @Override
            public void execute() {
                tv.changeChannel();
            }

        });
        control.request(new Command() {

            @Override
            public void execute() {
                tv.turnOff();
            }

        });
    }

}
