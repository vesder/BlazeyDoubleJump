package me.vesder.blazeydoublejump.listeners;

import me.vesder.blazeydoublejump.utils.VoidUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import static me.vesder.blazeydoublejump.utils.JumpUtility.*;
import static me.vesder.blazeydoublejump.utils.TextUtils.*;

public class InfiniteJump implements Listener {

    @EventHandler
    private void onToggleFlight(PlayerToggleFlightEvent e) {

        Player player = e.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE ||
            player.getGameMode() == GameMode.SPECTATOR ||
            !e.isFlying()) return;

        if (isJumpOnCooldown(player.getUniqueId())) {

            VoidUtils.sendMsg(player,getStringConfig("Actions.Errors.Cooldown.message"));
            VoidUtils.playStringSound(player,getStringConfig("Actions.Errors.Cooldown.sound"));
            e.setCancelled(true);
            return;
        }

        VoidUtils.sendMsg(player, getStringConfig("Actions.OnJump.message"));
        VoidUtils.playStringSound(player, getStringConfig("Actions.OnJump.sound"));

        player.setVelocity(player.getLocation().getDirection().multiply(LAUNCH).setY(LAUNCHY));
        setLastJumpTime(player.getUniqueId());
        e.setCancelled(true);

    }

}