package me.vesder.blazeydoublejump.utils;

import java.util.HashMap;
import java.util.UUID;

import static me.vesder.blazeydoublejump.utils.TextUtils.getDoubleConfig;

public class JumpUtility {

    public static final double
            LAUNCH = getDoubleConfig("Settings.Launch", 10D),
            LAUNCHY = getDoubleConfig("Settings.LaunchY", 10D),
            COOLDOWN = getDoubleConfig("Settings.Cooldown") * 1000L;

    private static final HashMap<UUID, Boolean> jumpStatus = new HashMap<>();
    private static final HashMap<UUID, Long> lastJumpTime = new HashMap<>();

    public static boolean getJumpStatus(UUID playerId) {
        return jumpStatus.get(playerId);
    }

    public static void setJumpStatus(UUID playerId, Boolean status) {
        jumpStatus.put(playerId, status);
    }

    public static boolean isJumpOnCooldown(UUID playerId) {

        if (!lastJumpTime.containsKey(playerId)) return false;

        return System.currentTimeMillis() - lastJumpTime.get(playerId) < COOLDOWN;
    }

    public static double getJumpCooldown(UUID playerId) {
        return COOLDOWN - (System.currentTimeMillis() - lastJumpTime.get(playerId)) / 1000D;
    }

    public static void setLastJumpTime(UUID playerId) {
        lastJumpTime.put(playerId, System.currentTimeMillis());
    }

}
