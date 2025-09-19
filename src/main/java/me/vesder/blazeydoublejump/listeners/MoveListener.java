package me.vesder.blazeydoublejump.listeners;

import me.vesder.blazeydoublejump.config.ConfigManager;
import me.vesder.blazeydoublejump.config.customconfigs.SettingsConfig;
import me.vesder.blazeydoublejump.data.User;
import me.vesder.blazeydoublejump.data.UserManager;
import me.vesder.blazeydoublejump.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static me.vesder.blazeydoublejump.utils.Utils.checkPermission;

public class MoveListener implements Listener {

    SettingsConfig settingsConfig = (SettingsConfig) ConfigManager.getConfigManager().getCustomConfig("settings.yml");

    @EventHandler
    private void onMove(PlayerMoveEvent e) {

        Player player = e.getPlayer();
        User user = UserManager.getUser(player.getUniqueId());

        if (!checkPermission(player, "blazeydoublejump.infinitejump")) {

            if (!player.getAllowFlight()) player.setAllowFlight(true);
            if (user.isJumpAllowed() || !player.isOnGround()) return;

            user.setJumpAllowed(true);
            for (String action : settingsConfig.getActiveActions()) {
                Utils.runActionDispatcher(action, player, player);
            }
        }

    }

}