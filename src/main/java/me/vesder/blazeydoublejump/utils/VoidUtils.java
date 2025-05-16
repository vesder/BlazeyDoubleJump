package me.vesder.blazeydoublejump.utils;

import org.bukkit.entity.Player;

import static me.vesder.blazeydoublejump.utils.TextUtils.color;
import static me.vesder.blazeydoublejump.utils.TextUtils.getStringConfig;

public class VoidUtils {

    private static final String prefix = getStringConfig("Messages.Prefix");

    public static void sendMsg(Player player, String message) {

        if (message.isEmpty()) {
            return;
        }

        player.sendMessage(color(prefix + message));
    }

}
