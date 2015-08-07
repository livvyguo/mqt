package org.lvy.mqt.utils;

import java.util.ResourceBundle;

/**
 * Created by livvy on 15/8/7.
 */
public final class ConfigUtils {
    private ConfigUtils() {
        throw new AssertionError();
    }

    private static final ResourceBundle CONFIG = ResourceBundle.getBundle("config");


    public static String getConfig(String key) {
        return CONFIG.getString(key);
    }
}
