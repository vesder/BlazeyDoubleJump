package me.vesder.blazeydoublejump.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static me.vesder.blazeydoublejump.utils.TextUtils.color;
import static me.vesder.blazeydoublejump.utils.TextUtils.getStringConfig;

public class VoidUtils {

    private static final String prefix = getStringConfig("Messages.Prefix");

    public static void sendMsg(Player player, String message) {

        if (message.isEmpty()) return;

        player.sendMessage(color(prefix + message));
    }

    public static void playStringSound(Player player, String sound) {

        if (sound.isEmpty()) return;

        player.playSound(player.getLocation(), Sound.valueOf(sound), 5.0F, 1.0F);
    }

}
