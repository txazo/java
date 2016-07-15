package org.txazo.designpattern.behavior.command.core;

public class OffCommand extends AbstractCommand {

    public OffCommand(TV tv) {
        super(tv);
    }

    @Override
    public void execute() {
        tv.turnOff();
    }

}
