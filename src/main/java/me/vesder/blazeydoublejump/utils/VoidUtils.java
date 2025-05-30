package me.vesder.blazeydoublejump.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import java.util.Map;

import static me.vesder.blazeydoublejump.utils.ConfigUtils.getStringConfig;
import static me.vesder.blazeydoublejump.utils.TextUtils.color;

public class VoidUtils {

    private static final String prefix = getStringConfig("Actions.prefix");

    public static void sendMessage(Player player, String configPath) {

        String message = getStringConfig(configPath);

        if (message.isEmpty()) return;

        player.sendMessage(color(prefix + message));
    }

    public static void sendMessage(Player player, String configPath, Map<String, Object> placeholders) {

        String message = getStringConfig(configPath);

        if (message.isEmpty()) return;

        for (Map.Entry<String, Object> entry : placeholders.entrySet()) {
            message = message.replace(entry.getKey(), String.valueOf(entry.getValue()));
        }

        player.sendMessage(color(prefix + message));
    }

    public static void playStringSound(Player player, String configPath) {

        String sound = getStringConfig(configPath);

        if (sound.isEmpty()) return;

        player.playSound(player.getLocation(), Sound.valueOf(sound), 5.0F, 1.0F);
    }

}
