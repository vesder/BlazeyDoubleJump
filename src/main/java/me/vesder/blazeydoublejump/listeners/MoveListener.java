package me.vesder.blazeydoublejump.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static me.vesder.blazeydoublejump.listeners.JoinListener.doubleJumpStatus;
import static me.vesder.blazeydoublejump.utils.TextUtils.color;
import static me.vesder.blazeydoublejump.utils.TextUtils.getStringConfig;

public class MoveListener implements Listener {

    private final String doubleJumpActive = color(getStringConfig("Messages.DoubleJumpIsActive"));

    @EventHandler
    private void onMove(PlayerMoveEvent e) {

        Player player = e.getPlayer();

        if (!doubleJumpStatus.get(player.getUniqueId())) {
            if (player.isOnGround()) {

                doubleJumpStatus.put(player.getUniqueId(), true);
                if (!doubleJumpActive.isEmpty()) {
                    player.sendMessage(doubleJumpActive);
                }

            }
        }

    }

}