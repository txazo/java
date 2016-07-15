package org.txazo.designpattern.behavior.command.core;

public class OnCommand extends AbstractCommand {

    public OnCommand(TV tv) {
        super(tv);
    }

    @Override
    public void execute() {
        tv.turnOn();
    }

}
