package bredo.cmd.treeshifter.validator;

import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public final class ValueValidator {

    private final static String[] trueValues;
    private final static String[] falseValues;

    private final static Map<Boolean, Integer> emptyInt;
    private final static Map<Boolean, Double> emptyDouble;

    static {
        trueValues = new String[]{"Enable", "True", "On", "Yes", "1"};
        falseValues = new String[]{"Disable", "False", "Off", "No", "0"};
        emptyInt = new HashMap<>() {{
            put(false, 0);
        }};
        emptyDouble = new HashMap<>() {{
            put(false, 0D);
        }};
    }

    public static boolean isFalse(final String value) {
        for (final String falseValue : falseValues) if (falseValue.equalsIgnoreCase(value)) return true;
        return false;
    }

    public static boolean isTrue(final String value) {
        for (final String trueValue : trueValues) if (trueValue.equalsIgnoreCase(value)) return true;
        return false;
    }

    public static Map<Boolean, Integer> getIntAndAlert(final String value, final CommandSender commandSender) {
        final Map<Boolean, Integer> intValue = getInt(value);
        if (intValue.containsKey(false)) commandSender.sendMessage("Please use valid int!");
        return intValue;
    }

    public static Map<Boolean, Integer> getInt(final String value) {
        if (value == null || value.isEmpty()) return emptyInt;

        try {
            return new HashMap<>() {{
                put(true, Integer.parseInt(value));
            }};
        } catch (final Exception exception) {
            return emptyInt;
        }
    }

    public static Map<Boolean, Double> getDoubleAndAlert(final String value, final CommandSender commandSender) {
        final Map<Boolean, Double> doubleValue = getDouble(value);
        if (doubleValue.containsKey(false)) commandSender.sendMessage("Please use valid double!");
        return doubleValue;
    }

    public static Map<Boolean, Double> getDouble(final String value) {
        if (value == null || value.isEmpty()) return emptyDouble;

        try {
            return new HashMap<>() {{
                put(true, Double.parseDouble(value));
            }};
        } catch (final Exception exception) {
            return emptyDouble;
        }
    }

    public static String[] getTrueValues() {
        return trueValues;
    }

    public static String[] getFalseValues() {
        return falseValues;
    }
}