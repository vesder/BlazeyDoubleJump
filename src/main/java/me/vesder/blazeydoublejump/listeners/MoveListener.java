package me.vesder.blazeydoublejump.listeners;

import me.vesder.blazeydoublejump.utils.VoidUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static me.vesder.blazeydoublejump.utils.JumpUtility.setJumpStatus;
import static me.vesder.blazeydoublejump.utils.TextUtils.getStringConfig;

public class MoveListener implements Listener {

    @EventHandler
    private void onMove(PlayerMoveEvent e) {

        Player player = e.getPlayer();

        if (player.getAllowFlight() || !player.isOnGround()) return;

        setJumpStatus(player.getUniqueId(), true);
        VoidUtils.sendMsg(player, getStringConfig("Messages.DoubleJumpIsActive"));

    }

}