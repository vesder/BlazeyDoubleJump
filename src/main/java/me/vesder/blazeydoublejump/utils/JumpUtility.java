package me.vesder.blazeydoublejump.utils;

import me.vesder.blazeydoublejump.config.ConfigManager;
import me.vesder.blazeydoublejump.config.customconfigs.SettingsConfig;
import me.vesder.blazeydoublejump.data.User;
import me.vesder.blazeydoublejump.data.UserManager;

import java.util.UUID;

public class JumpUtility {

    private static final SettingsConfig settingsConfig = (SettingsConfig) ConfigManager.getConfigManager().getCustomConfig("settings.yml");

    /**
     * Checks if the player is currently on cooldown and cannot jump yet.
     *
     * @param playerId the UUID of the player
     * @return true if the player is on cooldown; false otherwise
     */
    public static boolean isJumpOnCooldown(UUID playerId) {

        User user = UserManager.getUser(playerId);

        if (user.getLastJumpTime() == 0) return false;

        return System.currentTimeMillis() - user.getLastJumpTime() < settingsConfig.getCooldown() * 1000L;
    }

    /**
     * Returns the remaining cooldown time in seconds before the player can jump again.
     *
     * @param playerId the UUID of the player
     * @return remaining cooldown time in seconds, or 0 if no cooldown
     */
    public static int getJumpCooldownLeft(UUID playerId) {

        User user = UserManager.getUser(playerId);

        if (user.getLastJumpTime() == 0) return 0;

        return (int) ((settingsConfig.getCooldown() * 1000L - (System.currentTimeMillis() - user.getLastJumpTime())) / 1000);
    }

}
