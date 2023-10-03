package bredo.cmd.treeshifter.commands;

import bredo.cmd.treeshifter.commands.arguments.ToggleArgument;
import bredo.cmd.treeshifter.interfaces.CommandArgument;
import bredo.cmd.treeshifter.interfaces.TreeShifterInstance;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.HashSet;

public final class TreeShifterCommand extends BukkitCommand implements TreeShifterInstance {

    private final static TreeShifterCommand SINGLETON;

    static {
        SINGLETON = new TreeShifterCommand("TreeShifter");
    }

    private final HashSet<CommandArgument> commandArguments;

    public TreeShifterCommand(final String name) {
        super(name);
        commandArguments = new HashSet<>();
        addArgument(new ToggleArgument("Toggle", "Toggle"));
    }

    @Override
    public boolean execute(final CommandSender commandSender, final String commandLabel, final String[] args) {
        if (args.length == 0) {
            ArgumentDefault.getInstance().execute(commandSender);
            return true;
        }

        if (args.length == 1) {
            final CommandArgument commandArgument = getArgument(args[0]);
            if (commandArgument == null) sendMessage(commandSender, "command.invalidArgument");
            else commandArgument.logicSingle(commandSender);
            return true;
        }

        if (args.length == 2) {
            final CommandArgument commandArgument = getArgument(args[0]);
            if (commandArgument == null) sendMessage(commandSender, "command.invalidArgument");
            else commandArgument.logicArgument(commandSender, args[1]);
            return true;
        }

        sendMessage(commandSender, "command.tooMany");
        return true;
    }

    public static TreeShifterCommand getInstance() {
        return SINGLETON;
    }

    public CommandArgument getArgument(final String argument) {
        for (final CommandArgument commandArgument : getCommandArguments())
            if (commandArgument.getArgument().equalsIgnoreCase(argument)) return commandArgument;
        return null;
    }

    public void addArgument(final CommandArgument commandArgument) {
        getCommandArguments().add(commandArgument);
    }

    public HashSet<CommandArgument> getCommandArguments() {
        return commandArguments;
    }
}
