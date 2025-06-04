package me.vesder.blazeydoublejump.jumps;

import java.util.HashMap;
import java.util.UUID;

import static me.vesder.blazeydoublejump.config.ConfigUtils.getDoubleConfig;

public class JumpUtility {

    public static final double
        LAUNCH = getDoubleConfig("Settings.Launch", 10D),
        LAUNCHY = getDoubleConfig("Settings.LaunchY", 10D),
        COOLDOWN = getDoubleConfig("Settings.Cooldown") * 1000L;

    private static final HashMap<UUID, Boolean> jumpStatus = new HashMap<>();
    private static final HashMap<UUID, Long> lastJumpTime = new HashMap<>();

    /**
     * Sets whether the player is currently allowed to jump.
     *
     * @param playerId the UUID of the player
     * @param status   true if the player is allowed to jump; false otherwise
     */
    public static void setJumpAllowed(UUID playerId, Boolean status) {
        jumpStatus.put(playerId, status);
    }

    /**
     * Returns whether the player is currently allowed to jump.
     *
     * @param playerId the UUID of the player
     * @return true if the player is allowed to jump; false otherwise
     */
    public static boolean isJumpAllowed(UUID playerId) {
        return jumpStatus.get(playerId);
    }

    /**
     * Records the current time as the player's last jump time.
     *
     * @param playerId the UUID of the player
     */
    public static void setLastJumpTime(UUID playerId) {
        lastJumpTime.put(playerId, System.currentTimeMillis());
    }

    /**
     * Checks if the player is currently on cooldown and cannot jump yet.
     *
     * @param playerId the UUID of the player
     * @return true if the player is on cooldown; false otherwise
     */
    public static boolean isJumpOnCooldown(UUID playerId) {

        if (!lastJumpTime.containsKey(playerId)) return false;

        return System.currentTimeMillis() - lastJumpTime.get(playerId) < COOLDOWN;
    }

    /**
     * Returns the remaining cooldown time in seconds before the player can jump again.
     *
     * @param playerId the UUID of the player
     * @return remaining cooldown time in seconds, or 0 if no cooldown
     */
    public static int getJumpCooldownLeft(UUID playerId) {

        if (!lastJumpTime.containsKey(playerId)) return 0;

        return (int) ((COOLDOWN - (System.currentTimeMillis() - lastJumpTime.get(playerId))) / 1000);
    }

}
