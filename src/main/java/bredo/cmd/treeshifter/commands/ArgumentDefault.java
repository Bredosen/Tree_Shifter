package bredo.cmd.treeshifter.commands;

import bredo.cmd.treeshifter.interfaces.TreeShifterInstance;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class ArgumentDefault implements TreeShifterInstance {

    private final static ArgumentDefault SINGLETON;

    static {
        SINGLETON = new ArgumentDefault();
    }

    public static ArgumentDefault getInstance() {
        return SINGLETON;
    }

    public void execute(final CommandSender commandSender) {
        if (commandSender instanceof Player player) {
            sendMessage(player, "open GUI");
            return;
        }
        commandSender.sendMessage("information");
    }
}
