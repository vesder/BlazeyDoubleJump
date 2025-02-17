package me.vesder.blazeydoublejump.utils;

import org.bukkit.ChatColor;

import static me.vesder.blazeydoublejump.BlazeyDoubleJump.getPlugin;

public class TextUtils {

    public static String color(String string) {

        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String getStringConfig(String path) {

        return getPlugin().getConfig().getString(path);
    }

    public static double getDoubleConfig(String path) {

        return Math.min(getPlugin().getConfig().getDouble(path), 10);
    }

    public static boolean getBooleanConfig(String path) {

        return getPlugin().getConfig().getBoolean(path);
    }

}