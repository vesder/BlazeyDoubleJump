package me.vesder.blazeydoublejump.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static me.vesder.blazeydoublejump.utils.JumpUtility.getJumpCooldownLeft;

public class TextUtils {

    public static String color(String string) {

        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String color(String string, Player player) {

        string = string.replace("%cooldown%", String.valueOf(getJumpCooldownLeft(player.getUniqueId())));
        string = string.replace("%player_name%", player.getDisplayName());

        return ChatColor.translateAlternateColorCodes('&', string);
    }
}