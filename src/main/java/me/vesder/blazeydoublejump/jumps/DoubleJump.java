package me.vesder.blazeydoublejump.jumps;

import java.util.HashMap;
import java.util.Map;

import me.vesder.blazeydoublejump.data.User;
import me.vesder.blazeydoublejump.data.UserManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import me.vesder.blazeydoublejump.listeners.JoinListener;
import me.vesder.blazeydoublejump.listeners.MoveListener;
import me.vesder.blazeydoublejump.utils.VoidUtils;

import static me.vesder.blazeydoublejump.BlazeyDoubleJump.getPlugin;
import static me.vesder.blazeydoublejump.jumps.JumpUtility.LAUNCH;
import static me.vesder.blazeydoublejump.jumps.JumpUtility.LAUNCHY;
import static me.vesder.blazeydoublejump.jumps.JumpUtility.getJumpCooldownLeft;
import static me.vesder.blazeydoublejump.jumps.JumpUtility.isJumpOnCooldown;
import static org.bukkit.Bukkit.getPluginManager;

public class DoubleJump implements Listener {

    public DoubleJump() {

        getPluginManager().registerEvents(new MoveListener(), getPlugin());
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

        User user = UserManager.getUser(player.getUniqueId());
        if (!user.isJumpAllowed()) {

            VoidUtils.sendMessage(player, "Actions.Errors.AlreadyJumped.message", placeholders);
            VoidUtils.playStringSound(player, "Actions.Errors.AlreadyJumped.sound");
            return;
        }

        if (isJumpOnCooldown(player.getUniqueId())) {

            VoidUtils.sendMessage(player, "Actions.Errors.Cooldown.message", placeholders);
            VoidUtils.playStringSound(player, "Actions.Errors.Cooldown.sound");
            return;
        }

        VoidUtils.sendMessage(player, "Actions.OnJump.message", placeholders);
        VoidUtils.playStringSound(player, "Actions.OnJump.sound");

        player.setVelocity(player.getLocation().getDirection().multiply(LAUNCH).setY(LAUNCHY));

        user.setJumpAllowed(false);
        user.setLastJumpTime(System.currentTimeMillis());
    }

}