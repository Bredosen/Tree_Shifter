package bredo.cmd.treeshifter.interfaces;

import bredo.cmd.treeshifter.commands.TreeShifterCommand;
import bredo.cmd.treeshifter.managers.DataManager;
import org.bukkit.command.CommandSender;

public interface TreeShifterInstance {

    default void sendMessage(final CommandSender commandSender, final String path) {
        commandSender.sendMessage(getString(path, "Unknown Path: \"" + path + "\""));
    }

    default String getString(final String path, final String defaultValue) {
        return getDataManager().getString(path, defaultValue);
    }

    default TreeShifterCommand getTreeShifterCommand() {
        return TreeShifterCommand.getInstance();
    }

    default DataManager getDataManager() {
        return DataManager.getInstance();
    }
}
