package bredo.cmd.treeshifter.handlers;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The ColorEncoder class handles encoding and decoding color codes in messages.
 */
public final class ColorEncoder {

    /**
     * Sends a colored message to the specified player.
     *
     * @param player  The player to whom the message will be sent.
     * @param message The message to be sent.
     */
    public static void sendMessage(final Player player, final String message) {
        player.spigot().sendMessage(TextComponent.fromLegacyText(encodeColor(message)));
    }

    /**
     * Sends a colored message to the specified command sender.
     * If the sender is a player, the message will be colored accordingly.
     *
     * @param commandSender The command sender to whom the message will be sent.
     * @param message       The message to be sent.
     */
    public static void sendMessage(final CommandSender commandSender, final String message) {
        if (commandSender instanceof Player player) sendMessage(player, message);
        else commandSender.sendMessage(removeColor(message));
    }

    /**
     * Removes color codes from a message.
     *
     * @param message The input message containing color codes.
     *
     * @return The message without color codes.
     */
    private static String removeColor(final String message) {
        return message.replaceAll("#([0-9a-fA-F]{6})", "");
    }

    /**
     * Encodes color codes in the message and returns the formatted string.
     *
     * @param message The input message containing color codes.
     *
     * @return The formatted message with color.
     */
    public static String encodeColor(final String message) {
        final Pattern pattern = Pattern.compile("#([0-9a-fA-F]{6})");
        final String[] parts = message.split("(?=#([0-9a-fA-F]{6}))");
        final StringBuilder formattedString = new StringBuilder();

        for (final String part : parts) {
            final Matcher matcher = pattern.matcher(part);
            String plainText = part;

            if (matcher.find()) {
                String colorCode = matcher.group(1); // Extract the color code
                plainText = part.replace("#" + colorCode, ""); // Remove the color code
                final ChatColor color = ChatColor.of("#" + colorCode); // Create ChatColor
                formattedString.append(color).append(plainText);
            } else formattedString.append(plainText);

        }

        return formattedString.toString();
    }
}
