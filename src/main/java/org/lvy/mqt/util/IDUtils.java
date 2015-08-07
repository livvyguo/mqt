package org.lvy.mqt.util;

import java.util.UUID;

/**
 * Created by livvy on 15/8/7.
 */
public final class IDUtils {
    private IDUtils() {
        throw new AssertionError();
    }

    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
