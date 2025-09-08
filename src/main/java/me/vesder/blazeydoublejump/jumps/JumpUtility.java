package me.vesder.blazeydoublejump.jumps;

import me.vesder.blazeydoublejump.data.User;
import me.vesder.blazeydoublejump.data.UserManager;

import java.util.UUID;

import static me.vesder.blazeydoublejump.config.ConfigUtils.getDoubleConfig;

public class JumpUtility {

    public static final double
        LAUNCH = getDoubleConfig("Settings.Launch", 10D),
        LAUNCHY = getDoubleConfig("Settings.LaunchY", 10D),
        COOLDOWN = getDoubleConfig("Settings.Cooldown") * 1000L;

    /**
     * Checks if the player is currently on cooldown and cannot jump yet.
     *
     * @param playerId the UUID of the player
     * @return true if the player is on cooldown; false otherwise
     */
    public static boolean isJumpOnCooldown(UUID playerId) {

        User user = UserManager.getUser(playerId);

        if (user.getLastJumpTime() == 0) return false;

        return System.currentTimeMillis() - user.getLastJumpTime() < COOLDOWN;
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

        return (int) ((COOLDOWN - (System.currentTimeMillis() - user.getLastJumpTime())) / 1000);
    }

}
