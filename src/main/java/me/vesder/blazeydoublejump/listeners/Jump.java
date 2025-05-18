package me.vesder.blazeydoublejump.listeners;

import java.util.HashMap;
import java.util.UUID;

import static me.vesder.blazeydoublejump.utils.TextUtils.getDoubleConfig;

public abstract class Jump {

    final double LAUNCH = getDoubleConfig("Settings.Launch"),
                 LAUNCHY = getDoubleConfig("Settings.LaunchY"),
                 COOLDOWN = getDoubleConfig("Settings.Cooldown");

    private final HashMap<UUID, Boolean> jumpStatus = new HashMap<>();
    private final HashMap<UUID, Long> lastJumpTime = new HashMap<>();

    boolean getJumpStatus(UUID playerId) {
        return jumpStatus.get(playerId);
    }

    void setJumpStatus(UUID playerId, Boolean status) {
        jumpStatus.put(playerId, status);
    }

    boolean isJumpOnCooldown(UUID playerId) {
        return System.currentTimeMillis() - lastJumpTime.get(playerId) < COOLDOWN;
    }

    void setLastJumpTime(UUID playerId) {
        lastJumpTime.put(playerId, System.currentTimeMillis());
    }

}
