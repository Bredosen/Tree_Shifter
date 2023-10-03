package bredo.cmd.treeshifter;

import bredo.cmd.treeshifter.commands.TreeShifterCommand;
import bredo.cmd.treeshifter.interfaces.TreeShifterInstance;
import bredo.cmd.treeshifter.managers.CommandManager;
import bredo.cmd.treeshifter.managers.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TreeShifter extends JavaPlugin implements TreeShifterInstance {

    private static TreeShifter SINGLETON;

    @Override
    public void onLoad() {
        SINGLETON = this;
    }

    @Override
    public void onEnable() {
        // Load DataManager
        getDataManager().load();

        // Extract commandMap from server.
        CommandManager.getInstance().extractCommandMap();

        // Register commands
        CommandManager.getInstance().registerCommand(TreeShifterCommand.getInstance());

        // Register event to pluginManager.
        EventManager.getInstance().registerEvent(null/*TEMP*/);
    }

    @Override
    public void onDisable() {
        // Save DataManager
        getDataManager().save();
    }

    public static TreeShifter getInstance() {
        return SINGLETON;
    }
}
