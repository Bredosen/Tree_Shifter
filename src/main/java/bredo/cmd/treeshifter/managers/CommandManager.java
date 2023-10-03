package bredo.cmd.treeshifter.managers;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.command.defaults.BukkitCommand;

import java.lang.reflect.Field;

public final class CommandManager {
    private final static CommandManager SINGLETON;

    static {
        SINGLETON = new CommandManager();
    }

    private SimpleCommandMap simpleCommandMap;

    public void registerCommand(final BukkitCommand bukkitCommand) {
        if (getSimpleCommandMap() == null) throw new NullPointerException("SimpleCommandMap is not yet extracted!");
        getSimpleCommandMap().register(bukkitCommand.getName(), bukkitCommand);
    }

    public void extractCommandMap() {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            if (bukkitCommandMap.get(Bukkit.getServer()) instanceof SimpleCommandMap simpleCommandMap)
                this.simpleCommandMap = simpleCommandMap;
        } catch (final NoSuchFieldException | IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }

    public SimpleCommandMap getSimpleCommandMap() {
        return simpleCommandMap;
    }

    public static CommandManager getInstance() {
        return SINGLETON;
    }
}
