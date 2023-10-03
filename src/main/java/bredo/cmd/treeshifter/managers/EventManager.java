package bredo.cmd.treeshifter.managers;

import bredo.cmd.treeshifter.TreeShifter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public final class EventManager {

    private final static EventManager SINGLETON;

    static {
        SINGLETON = new EventManager();
    }

    public void registerEvent(final Listener listener) {
        getPluginManager().registerEvents(listener, TreeShifter.getInstance());
    }

    public PluginManager getPluginManager() {
        return TreeShifter.getInstance().getServer().getPluginManager();
    }

    public static EventManager getInstance() {
        return SINGLETON;
    }
}
