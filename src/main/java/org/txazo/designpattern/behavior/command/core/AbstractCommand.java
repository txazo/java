package org.txazo.designpattern.behavior.command.core;

public abstract class AbstractCommand implements Command {

    protected TV tv;

    public AbstractCommand(TV tv) {
        this.tv = tv;
    }

}
