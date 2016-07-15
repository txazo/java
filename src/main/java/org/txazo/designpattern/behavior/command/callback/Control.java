package org.txazo.designpattern.behavior.command.callback;

public class Control {

    public void request(Command command) {
        command.execute();
    }

}
