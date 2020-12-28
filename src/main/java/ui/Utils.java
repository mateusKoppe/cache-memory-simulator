package ui;

public class Utils {
    public static String formatBinary (int value) {
        return Utils.formatBinary(value, 8);
    }

    public static String formatBinary (int value, int size) {
        String output = Integer.toBinaryString(value);
        while (output.length() < size) output = "0" + output;
        return output;
    }
}
