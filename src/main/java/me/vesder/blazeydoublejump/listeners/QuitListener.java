package me.vesder.blazeydoublejump.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.vesder.blazeydoublejump.listeners.JoinListener.doubleJumpStatus;

public class QuitListener implements Listener {

    @EventHandler
    private void onQuit(PlayerQuitEvent e) {
        doubleJumpStatus.remove(e.getPlayer().getUniqueId());
    }

}
