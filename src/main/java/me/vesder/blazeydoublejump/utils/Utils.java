package me.vesder.blazeydoublejump.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import me.vesder.blazeydoublejump.config.ConfigManager;
import me.vesder.blazeydoublejump.config.customconfigs.MessagesConfig;
import me.vesder.blazeydoublejump.config.customconfigs.SettingsConfig;
import me.vesder.blazeydoublejump.hooks.VaultHook;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.vesder.blazeydoublejump.utils.JumpUtility.getJumpCooldownLeft;

public class Utils {

    private static final SettingsConfig settingsConfig =
        (SettingsConfig) ConfigManager.getConfigManager().getCustomConfig("settings.yml");
    private static final MessagesConfig messagesConfig =
        (MessagesConfig) ConfigManager.getConfigManager().getCustomConfig("messages.yml");
    private static final boolean isPAPIEnabled = Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");

    Utils() {}

    public static void sendMessageAdmin(Player player, String value, String value2) {

        String message = messagesConfig.getConfigEditorSuccess();

        if (message.isEmpty()) return;

        player.sendMessage(color(settingsConfig.getPrefix() + String.format(message, value, value2)));
    }

    public static String color(String string) {

        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String parseWithPlaceholdersAPI(Player player, String text) {

        return PlaceholderAPI.setPlaceholders(player, text);
    }

    public static String formatText(String text, Player player) {

        if (isPAPIEnabled) {
            text = parseWithPlaceholdersAPI(player, text);
        }

        // -------------------------
        // Player-related placeholders
        // -------------------------
        text = text.replace(
            "<username>",
            player.getName()
        );
        text = text.replace(
            "<displayname>",
            player.getDisplayName()
        );
        text = text.replace(
            "<player-prefix>",
            VaultHook.hasChat() ? VaultHook.getChat().getPlayerPrefix(player) : ""
        );
        text = text.replace(
            "<player-suffix>",
            VaultHook.hasChat() ? VaultHook.getChat().getPlayerSuffix(player) : ""
        );
        text = text.replace(
            "<group>",
            VaultHook.hasPermissions() ? VaultHook.getPerms().getPrimaryGroup(player) : ""
        );
        text = text.replace(
            "<worldname>",
            player.getWorld().getName()
        );

        // -------------------------
        // Jump-related placeholders
        // -------------------------
        text = text.replace(
            "<cooldown>",
            String.valueOf(getJumpCooldownLeft(player.getUniqueId()))
        );

        return color(text);
    }

    public static void runActionDispatcher(String action, CommandSender target, Player player) {

        if (action.startsWith("CHAT:")) {
            action = action.substring(5).trim();
            target.sendMessage(formatText(settingsConfig.getPrefix() + action, player));
            return;
        }

        // Actions other than chat require player target
        if (!(target instanceof Player)) {
            return;
        }

        Player targetPlayer = (Player) target;

        if (action.startsWith("ACTIONBAR:")) {
            action = action.substring(10).trim();
            targetPlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(formatText(action, player)));
            return;
        }

        if (action.startsWith("TITLE:")) {
            action = action.substring(6).trim();
            targetPlayer.sendTitle(formatText(action, player), null);
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

    public static Boolean checkPermission(Player player, String permission) {

        return player.hasPermission(permission) || VaultHook.hasPermissions() && VaultHook.getPerms().playerHas(player, permission);
    }
}
