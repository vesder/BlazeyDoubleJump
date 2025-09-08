package me.vesder.blazeydoublejump.listeners;

import me.vesder.blazeydoublejump.data.User;
import me.vesder.blazeydoublejump.data.UserManager;
import me.vesder.blazeydoublejump.utils.VoidUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import java.util.HashMap;
import java.util.Map;

import static me.vesder.blazeydoublejump.jumps.JumpUtility.getJumpCooldownLeft;

public class MoveListener implements Listener {

    @EventHandler
    private void onMove(PlayerMoveEvent e) {

        Player player = e.getPlayer();
        User user = UserManager.getUser(player.getUniqueId());

        if (!player.getAllowFlight()) player.setAllowFlight(true);
        if (user.isJumpAllowed() || !player.isOnGround()) return;

        Map<String, Object> placeholders = new HashMap<>();
        placeholders.put("%cooldown%", getJumpCooldownLeft(player.getUniqueId()));
        placeholders.put("%player_name%", player.getDisplayName());

        user.setJumpAllowed(true);
        VoidUtils.sendMessage(player, "Actions.OnActive.message", placeholders);
        VoidUtils.playStringSound(player, "Actions.OnActive.sound");

    }

}