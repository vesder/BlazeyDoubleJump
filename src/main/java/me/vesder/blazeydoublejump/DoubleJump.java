package me.vesder.blazeydoublejump;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import static me.vesder.blazeydoublejump.listeners.JoinListener.doubleJumpStatus;
import static me.vesder.blazeydoublejump.utils.TextUtils.*;

public class DoubleJump implements Listener {

    private final double launch = getDoubleConfig("Settings.Launch_power"),
            launchY = getDoubleConfig("Settings.Launch_power_y");

    private final String doubleJumpMsg = color(getStringConfig("Messages.DoubleJump")),
            alreadyJumpedMsg = color(getStringConfig("Messages.AlreadyDoubleJumped"));

    private final Sound jumpSound = Sound.valueOf(getStringConfig("Settings.Sound")),
            errorSound = Sound.valueOf(getStringConfig("Settings.SoundError"));

    @EventHandler
    private void onToggleFlight(PlayerToggleFlightEvent e) {

        Player player = e.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR || !e.isFlying()) {
            return;
        } else if (!doubleJumpStatus.get(player.getUniqueId())) {

            if (!alreadyJumpedMsg.isEmpty()) {
                player.sendMessage(alreadyJumpedMsg);
            }
            player.playSound(player.getLocation(), errorSound, 5.0F, 1.0F);
            e.setCancelled(true);
            return;
        }

        if (!doubleJumpMsg.isEmpty()) {
            player.sendMessage(doubleJumpMsg);
        }
        player.playSound(player.getLocation(), jumpSound, 5.0F, 1.0F);
        player.setVelocity(player.getLocation().getDirection().multiply(launch).setY(launchY));
        doubleJumpStatus.put(player.getUniqueId(), false);
        e.setCancelled(true);

    }

}