package bredo.cmd.treeshifter.commands.arguments;

import bredo.cmd.treeshifter.interfaces.CommandArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class ToggleArgument extends CommandArgument {

    public ToggleArgument(final String callName, final String argument) {
        super(callName, argument);
    }

    @Override
    public void logicSingle(final CommandSender commandSender) {
        if (commandSender instanceof Player player) {
            sendMessage(player, "player toggle");
            return;
        }

        executeSingle("GlobalToggle", commandSender);
    }

    @Override
    public void logicArgument(final CommandSender commandSender, final String argument) {
        if (commandSender instanceof Player player) {
            sendMessage(player, "player toggle argument");
            return;
        }

        executeArgument("GlobalToggle", commandSender, argument);
    }
}
