package me.vesder.blazeydoublejump.utils;

import me.vesder.blazeydoublejump.config.ConfigManager;
import me.vesder.blazeydoublejump.config.customconfigs.MessagesConfig;
import me.vesder.blazeydoublejump.config.customconfigs.SettingsConfig;
import me.vesder.blazeydoublejump.hooks.VaultHook;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.vesder.blazeydoublejump.utils.JumpUtility.getJumpCooldownLeft;

public class Utils {

    // Void

    public static void sendMessageAdmin(Player player, String value, String value2) {

        String message = messagesConfig.getConfigEditorSuccess();

        if (message.isEmpty()) return;

        player.sendMessage(color(settingsConfig.getPrefix() + String.format(message, value, value2)));
    }

    public static void runActionDispatcher(String action, CommandSender target, Player player) {

        if (action.startsWith("CHAT:")) {
            action = action.substring(5).trim();
            target.sendMessage(color(settingsConfig.getPrefix() + action, player));
            return;
        }

        // PLAYER ONLY
        if (!(target instanceof Player)) {
            return;
        }

        Player targetPlayer = (Player) target;

        if (action.startsWith("ACTIONBAR:")) {
            action = action.substring(10).trim();
            targetPlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(color(action, player)));
            return;
        }

        if (action.startsWith("TITLE:")) { // sub title support later
            action = action.substring(6).trim();
            targetPlayer.sendTitle(color(action, player), null);
            return;
        }

        if (action.startsWith("SOUND:")) {
            action = action.substring(6).trim();
            targetPlayer.playSound(targetPlayer.getLocation(), Sound.valueOf(action), 5.0F, 1.0F);
            return;
        }

        if (action.startsWith("COMMAND:")) {
            action = action.substring(8).trim();
            targetPlayer.performCommand(action);
            return;
        }
    }

    // Other

    public static Boolean checkPermission(Player player, String permission) {

        return player.hasPermission(permission) || VaultHook.hasPermissions() && VaultHook.getPerms().playerHas(player, permission);
    }

    public static String color(String string) {

        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String color(String string, Player player) {

        string = string.replace("%cooldown%", String.valueOf(getJumpCooldownLeft(player.getUniqueId())));
        string = string.replace("%player_name%", player.getDisplayName());

        return ChatColor.translateAlternateColorCodes('&', string);
    }

    private static final SettingsConfig settingsConfig = (SettingsConfig) ConfigManager.getConfigManager().getCustomConfig("settings.yml");
    private static final MessagesConfig messagesConfig = (MessagesConfig) ConfigManager.getConfigManager().getCustomConfig("messages.yml");

}
