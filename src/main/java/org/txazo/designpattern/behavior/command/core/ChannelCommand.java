package org.txazo.designpattern.behavior.command.core;

public class ChannelCommand extends AbstractCommand {

    public ChannelCommand(TV tv) {
        super(tv);
    }

    @Override
    public void execute() {
        tv.changeChannel();
    }

}
