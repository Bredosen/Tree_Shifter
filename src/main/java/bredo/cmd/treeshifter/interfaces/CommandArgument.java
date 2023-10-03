package bredo.cmd.treeshifter.interfaces;

import bredo.cmd.treeshifter.commands.TreeShifterCommand;
import org.bukkit.command.CommandSender;

public abstract class CommandArgument implements TreeShifterInstance {

    private final String callName;
    private final String argument;

    public CommandArgument(final String callName, final String argument) {
        this.callName = callName;
        this.argument = argument;
    }

    public String getCallName() {
        return callName;
    }

    public String getArgument() {
        return argument;
    }

    public void executeSingle(final String callName, final CommandSender commandSender) {
        final CommandArgument commandArgument = getCall(callName);
        if (commandArgument == null) throw new NullPointerException("CommandArgument with callName \"" + callName + "\" does not exists");
        commandArgument.logicSingle(commandSender);
    }

    public void executeArgument(final String callName, final CommandSender commandSender, final String argument) {
        final CommandArgument commandArgument = getCall(callName);
        if (commandArgument == null) throw new NullPointerException("CommandArgument with callName \"" + callName + "\" does not exists");
        commandArgument.logicArgument(commandSender, argument);
    }

    public CommandArgument getCall(final String callName) {
        for (final CommandArgument commandArgument : getTreeShifterCommand().getCommandArguments())
            if (commandArgument.getCallName().equalsIgnoreCase(callName)) return commandArgument;
        return null;
    }

    public abstract void logicSingle(final CommandSender commandSender);

    public abstract void logicArgument(final CommandSender commandSender, final String argument);
}
