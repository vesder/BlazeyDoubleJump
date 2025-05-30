package me.vesder.blazeydoublejump.utils;

import static me.vesder.blazeydoublejump.BlazeyDoubleJump.getPlugin;

public class ConfigUtils {

    public static void setConfig(String path, Object value) {

        getPlugin().getConfig().set(path, value);
        getPlugin().saveConfig();
    }

    public static String getStringConfig(String path) {

        return getPlugin().getConfig().getString(path);
    }

    public static double getDoubleConfig(String path) {

        return getPlugin().getConfig().getDouble(path);
    }

    public static double getDoubleConfig(String path, Double max) {

        return Math.min(getPlugin().getConfig().getDouble(path), max);
    }

    public static boolean getBooleanConfig(String path) {

        return getPlugin().getConfig().getBoolean(path);
    }

}
