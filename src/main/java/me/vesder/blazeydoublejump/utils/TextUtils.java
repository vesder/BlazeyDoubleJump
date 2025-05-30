package me.vesder.blazeydoublejump.utils;

import org.bukkit.ChatColor;

public class TextUtils {

    public static String color(String string) {

        return ChatColor.translateAlternateColorCodes('&', string);
    }

}