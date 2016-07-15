package org.txazo.designpattern.behavior.command.core;

public class Control {

    private Command onCommand;
    private Command offCommand;
    private Command channelCommand;
    private Command autoCommand;

    public Control(Command onCommand, Command offCommand, Command channelCommand, Command autoCommand) {
        this.onCommand = onCommand;
        this.offCommand = offCommand;
        this.channelCommand = channelCommand;
        this.autoCommand = autoCommand;
    }

    public void turnOn() {
        onCommand.execute();
    }

    public void turnOff() {
        offCommand.execute();
    }

    public void changeChannel() {
        channelCommand.execute();
    }

    public void auto() {
        autoCommand.execute();
    }

}
