package org.lvy.mqt.util;

/**
 * Created by livvy on 15/8/8.
 */
public final class Utils {
    private Utils() {
        throw new AssertionError();
    }

    public static String getLineSeparator() {
        return System.getProperty("line.separator", "\n");
    }
}
