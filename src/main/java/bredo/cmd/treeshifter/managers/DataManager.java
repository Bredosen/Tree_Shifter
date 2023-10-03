package bredo.cmd.treeshifter.managers;

import bredo.cmd.treeshifter.TreeShifter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public final class DataManager extends YamlConfiguration {

    private final static DataManager SINGLETON;
    private final static String CONFIG_NAME;

    static {
        SINGLETON = new DataManager();
        CONFIG_NAME = "Config.yml";
    }

    private File file;

    public static DataManager getInstance() {
        return SINGLETON;
    }

    public void load() {
        if (notInitialized()) initialize();
        try {
            load(getFile());
        } catch (final IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
    }

    public void save() {
        if (notInitialized()) initialize();
        try {
            save(getFile());
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
    }

    public boolean notInitialized() {
        return getFile() == null || !getFile().isFile();
    }

    public void initialize() {
        if (getFile() != null) return;
        this.file = new File(TreeShifter.getInstance().getDataFolder(), CONFIG_NAME);

        final File directory = getFile().getParentFile();
        if (!directory.isDirectory()) {
            final boolean successful = directory.mkdirs();
        }

        if (!getFile().isFile()) {
            try {
                final boolean successful = getFile().createNewFile();
            } catch (final IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public File getFile() {
        return file;
    }
}
