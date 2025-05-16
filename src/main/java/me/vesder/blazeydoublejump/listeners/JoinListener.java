package me.vesder.blazeydoublejump.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE && player.getGameMode() == GameMode.SPECTATOR) return;

        if (!player.getAllowFlight()) player.setAllowFlight(true);

    }

}