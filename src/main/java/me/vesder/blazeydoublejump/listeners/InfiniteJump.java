package me.vesder.blazeydoublejump.listeners;

import me.vesder.blazeydoublejump.utils.VoidUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.HashMap;
import java.util.Map;

import static me.vesder.blazeydoublejump.utils.JumpUtility.*;

public class InfiniteJump implements Listener {

    @EventHandler
    private void onToggleFlight(PlayerToggleFlightEvent e) {

        Player player = e.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE ||
            player.getGameMode() == GameMode.SPECTATOR ||
            !e.isFlying()) return;

        Map<String, Object> placeholders = new HashMap<>();
        placeholders.put("%cooldown%", getJumpCooldown(player.getUniqueId()));
        placeholders.put("%player_name%", player.getDisplayName());

        e.setCancelled(true);

        if (isJumpOnCooldown(player.getUniqueId())) {

            VoidUtils.sendMsg(player, "Actions.Errors.Cooldown.message", placeholders);
            VoidUtils.playStringSound(player, "Actions.Errors.Cooldown.sound");
            return;
        }

        VoidUtils.sendMsg(player, "Actions.OnJump.message", placeholders);
        VoidUtils.playStringSound(player, "Actions.OnJump.sound");

        player.setVelocity(player.getLocation().getDirection().multiply(LAUNCH).setY(LAUNCHY));
        setLastJumpTime(player.getUniqueId());

    }

}