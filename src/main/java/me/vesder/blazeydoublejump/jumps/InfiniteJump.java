package me.vesder.blazeydoublejump.jumps;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import me.vesder.blazeydoublejump.listeners.JoinListener;
import me.vesder.blazeydoublejump.utils.VoidUtils;

import static me.vesder.blazeydoublejump.BlazeyDoubleJump.getPlugin;
import static me.vesder.blazeydoublejump.jumps.JumpUtility.LAUNCH;
import static me.vesder.blazeydoublejump.jumps.JumpUtility.LAUNCHY;
import static me.vesder.blazeydoublejump.jumps.JumpUtility.getJumpCooldownLeft;
import static me.vesder.blazeydoublejump.jumps.JumpUtility.isJumpOnCooldown;
import static me.vesder.blazeydoublejump.jumps.JumpUtility.setLastJumpTime;
import static org.bukkit.Bukkit.getPluginManager;

public class InfiniteJump implements Listener {


    public InfiniteJump() {

        getPluginManager().registerEvents(new JoinListener(), getPlugin());
    }

    @EventHandler
    private void onToggleFlight(PlayerToggleFlightEvent e) {

        Player player = e.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE
            || player.getGameMode() == GameMode.SPECTATOR
            || !e.isFlying()) {
            return;
        }

        Map<String, Object> placeholders = new HashMap<>();
        placeholders.put("%cooldown%", getJumpCooldownLeft(player.getUniqueId()));
        placeholders.put("%player_name%", player.getDisplayName());

        e.setCancelled(true);

        if (isJumpOnCooldown(player.getUniqueId())) {

            VoidUtils.sendMessage(player, "Actions.Errors.Cooldown.message", placeholders);
            VoidUtils.playStringSound(player, "Actions.Errors.Cooldown.sound");
            return;
        }

        VoidUtils.sendMessage(player, "Actions.OnJump.message", placeholders);
        VoidUtils.playStringSound(player, "Actions.OnJump.sound");

        player.setVelocity(player.getLocation().getDirection().multiply(LAUNCH).setY(LAUNCHY));
        setLastJumpTime(player.getUniqueId());
    }

}