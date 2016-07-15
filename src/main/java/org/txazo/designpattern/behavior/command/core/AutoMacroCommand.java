package org.txazo.designpattern.behavior.command.core;

import java.util.ArrayList;
import java.util.List;

public class AutoMacroCommand implements MacroCommand {

    private List<Command> commands = new ArrayList<>();

    @Override
    public void addCommand(Command command) {
        if (!commands.contains(command)) {
            commands.add(command);
        }
    }

    @Override
    public void removeCommand(Command command) {
        commands.remove(command);
    }

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

}
