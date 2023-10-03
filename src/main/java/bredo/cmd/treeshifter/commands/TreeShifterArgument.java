package bredo.cmd.treeshifter.commands;

import bredo.cmd.treeshifter.interfaces.TreeShifterInstance;
import bredo.cmd.treeshifter.validator.ValueValidator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public final class TreeShifterArgument implements TreeShifterInstance {

    private final static TreeShifterArgument SINGLETON;

    static {
        SINGLETON = new TreeShifterArgument();
    }

    public static TreeShifterArgument getInstance() {
        return SINGLETON;
    }

    //<editor-fold desc="Scan Distance">
    public boolean checkExecuteScanDistance(final CommandSender commandSender, final String[] args) {
        if (!args[0].equalsIgnoreCase("ScanDistance")) return false;

        if (args.length == 1) {
            commandSender.sendMessage("scanDistance is");
            commandSender.sendMessage("to set:");
            return true;
        }

        if (args.length == 2) {
            final Map<Boolean, Integer> value = ValueValidator.getIntAndAlert(args[1], commandSender);
            if (value.containsKey(false)) return true;
            sendMessage(commandSender, "scan distance is now");
            return true;
        }

        return true;
    }
    //</editor-fold>

    //<editor-fold desc="Execute Reload">
    public boolean checkExecuteReload(final CommandSender commandSender, final String[] args) {
        if (!args[0].equalsIgnoreCase("Reload")) return false;
        if (args.length == 1) {
            commandSender.sendMessage("command.argument.reload");
            return true;
        }

        if (args.length == 2) {
            if (!args[1].equalsIgnoreCase("Confirm")) return false;
            executeReload(commandSender);
            return true;
        }

        return false;
    }

    public void executeReload(final CommandSender commandSender) {
        commandSender.sendMessage("command.argument.reloadConfirm");
    }
    //</editor-fold>

    //<editor-fold desc="Execute toggle">
    public boolean checkExecuteToggle(final CommandSender commandSender, final String[] args) {
        if (!args[0].equalsIgnoreCase("Toggle")) return false;

        if (args.length == 1) {
            if (commandSender instanceof Player player) TreeShifterArgument.getInstance().executeToggle(player);
            else TreeShifterArgument.getInstance().executeGlobalToggle(commandSender);
            return true;
        }

        if (args.length == 2) {
            if (commandSender instanceof Player player) TreeShifterArgument.getInstance().executeToggle(player, args[1]);
            else TreeShifterArgument.getInstance().executeGlobalToggle(commandSender, args[1]);
            return true;
        }

        return false;
    }

    public void executeToggle(final Player player, final boolean toggleValue) {

    }

    public void executeToggle(final Player player, final String toggleValue) {
        if (ValueValidator.isTrue(toggleValue)) {
            executeToggle(player, true);
            return;
        }

        if (ValueValidator.isFalse(toggleValue)) {
            executeToggle(player, false);
            return;
        }

        sendMessage(player, "command.invalidBoolean");
    }

    public void executeToggle(final Player player) {
        executeToggle(player, true);
    }
    //</editor-fold>

    //<editor-fold desc="Execute debug">
    public boolean checkExecuteDebug(final CommandSender commandSender, final String[] args) {
        if (!args[0].equalsIgnoreCase("Debug")) return false;

        if (args.length == 1) {
            if (commandSender instanceof Player player) TreeShifterArgument.getInstance().executeDebug(player);
            else sendMessage(commandSender, "command.argument.debug.consoleCannot");
            return true;
        }

        if (args.length == 2) {
            if (commandSender instanceof Player player) TreeShifterArgument.getInstance().executeDebug(player, args[1]);
            else sendMessage(commandSender, "command.argument.debug.consoleCannot");
            return true;
        }

        return false;
    }

    public void executeDebug(final Player player, final boolean toggleValue) {

    }

    public void executeDebug(final Player player, final String toggleValue) {
        if (ValueValidator.isTrue(toggleValue)) {
            executeDebug(player, true);
            return;
        }

        if (ValueValidator.isFalse(toggleValue)) {
            executeDebug(player, false);
            return;
        }

        sendMessage(player, "command.invalidBoolean");
    }

    public void executeDebug(final Player player) {
        executeDebug(player, true);
    }
    //</editor-fold>

    //<editor-fold desc="Execute global toggle">
    public boolean checkExecuteGlobalToggle(final CommandSender commandSender, final String[] args) {
        if (!args[0].equalsIgnoreCase("GlobalToggle")) return false;

        if (args.length == 1) {
            TreeShifterArgument.getInstance().executeGlobalToggle(commandSender);
            return true;
        }

        if (args.length == 2) {
            TreeShifterArgument.getInstance().executeGlobalToggle(commandSender, args[1]);
            return true;
        }

        return false;
    }

    public void executeGlobalToggle(final CommandSender commandSender, final boolean toggleValue) {

    }

    public void executeGlobalToggle(final CommandSender commandSender, final String toggleValue) {
        if (ValueValidator.isTrue(toggleValue)) {
            executeGlobalToggle(commandSender, true);
            return;
        }

        if (ValueValidator.isFalse(toggleValue)) {
            executeGlobalToggle(commandSender, false);
            return;
        }

        sendMessage(commandSender, "command.invalidBoolean");
    }

    public void executeGlobalToggle(final CommandSender commandSender) {
        executeGlobalToggle(commandSender, true);
    }
    //</editor-fold>
}
