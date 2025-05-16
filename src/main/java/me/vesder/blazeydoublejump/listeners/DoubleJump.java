package me.vesder.blazeydoublejump.listeners;

import me.vesder.blazeydoublejump.utils.VoidUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import static me.vesder.blazeydoublejump.listeners.JoinListener.doubleJumpStatus;
import static me.vesder.blazeydoublejump.utils.TextUtils.*;

public class DoubleJump implements Listener {

    private final double LAUNCH = getDoubleConfig("Settings.Launch_power"),
            LAUNCHY = getDoubleConfig("Settings.Launch_power_y");

    @EventHandler
    private void onToggleFlight(PlayerToggleFlightEvent e) {

        Player player = e.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR || !e.isFlying()) {
            return;
        } else if (!doubleJumpStatus.get(player.getUniqueId())) {

            VoidUtils.sendMsg(player, getStringConfig("Messages.AlreadyDoubleJumped"));
            VoidUtils.playStringSound(player, getStringConfig("Settings.SoundError"));
            e.setCancelled(true);
            return;
        }

        VoidUtils.sendMsg(player, getStringConfig("Messages.DoubleJump"));
        VoidUtils.playStringSound(player, getStringConfig("Settings.Sound"));

        player.setVelocity(player.getLocation().getDirection().multiply(LAUNCH).setY(LAUNCHY));
        doubleJumpStatus.put(player.getUniqueId(), false);
        e.setCancelled(true);

    }

}