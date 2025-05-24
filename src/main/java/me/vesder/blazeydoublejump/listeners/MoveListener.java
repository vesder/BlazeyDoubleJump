package me.vesder.blazeydoublejump.listeners;

import me.vesder.blazeydoublejump.utils.VoidUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import java.util.HashMap;
import java.util.Map;

import static me.vesder.blazeydoublejump.jumps.JumpUtility.getJumpCooldownRemaining;
import static me.vesder.blazeydoublejump.jumps.JumpUtility.getJumpStatus;
import static me.vesder.blazeydoublejump.jumps.JumpUtility.setJumpStatus;

public class MoveListener implements Listener {

    @EventHandler
    private void onMove(PlayerMoveEvent e) {

        Player player = e.getPlayer();

        if (!player.getAllowFlight()) player.setAllowFlight(true);
        if (getJumpStatus(player.getUniqueId()) || !player.isOnGround()) return;

        Map<String, Object> placeholders = new HashMap<>();
        placeholders.put("%cooldown%", getJumpCooldownRemaining(player.getUniqueId()));
        placeholders.put("%player_name%", player.getDisplayName());

        e.setCancelled(true);

        setJumpStatus(player.getUniqueId(), true);
        VoidUtils.sendMessage(player, "Actions.OnActive.message", placeholders);
        VoidUtils.playStringSound(player, "Actions.OnActive.sound");

    }

}