package me.vesder.blazeydoublejump.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

import static me.vesder.blazeydoublejump.utils.TextUtils.getBooleanConfig;

public class JoinListener implements Listener {

    public static HashMap<UUID, Boolean> doubleJumpStatus = new HashMap<>();

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!getBooleanConfig("Settings.InfiniteJump")) {
            doubleJumpStatus.put(player.getUniqueId(), true);
        }

        if (player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR) {
            if (!player.getAllowFlight()) {
                player.getPlayer().setAllowFlight(true);
            }
        }
    }

}