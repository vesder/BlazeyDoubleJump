package me.vesder.blazeydoublejump.listeners;

import me.vesder.blazeydoublejump.config.ConfigManager;
import me.vesder.blazeydoublejump.config.customconfigs.SettingsConfig;
import me.vesder.blazeydoublejump.data.User;
import me.vesder.blazeydoublejump.data.UserManager;
import me.vesder.blazeydoublejump.utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import static me.vesder.blazeydoublejump.utils.JumpUtility.isJumpOnCooldown;
import static me.vesder.blazeydoublejump.utils.Utils.checkPermission;

public class ToggleFlightListener implements Listener {

    @EventHandler
    private void onToggleFlight(PlayerToggleFlightEvent e) {

        Player player = e.getPlayer();
        User user = UserManager.getUser(player.getUniqueId());

        if (player.getGameMode() == GameMode.CREATIVE
            || player.getGameMode() == GameMode.SPECTATOR
            || !e.isFlying()) {
            return;
        }

        e.setCancelled(true);

        boolean canInfiniteJump = checkPermission(player, "blazeydoublejump.infinitejump");

        if (!(checkPermission(player, "blazeydoublejump.doublejump") || canInfiniteJump)) {
            for (String action : settingsConfig.getDisabledErrorActions()) {
                Utils.runActionDispatcher(action, player, player);
            }
            return;
        }

        if (!canInfiniteJump && !user.isJumpAllowed()) {

            for (String action : settingsConfig.getUsedErrorActions()) {
                Utils.runActionDispatcher(action, player, player);
            }

            return;
        }

        if (isJumpOnCooldown(player.getUniqueId())) {

            for (String action : settingsConfig.getCooldownErrorActions()) {
                Utils.runActionDispatcher(action, player, player);
            }

            return;
        }

        for (String action : settingsConfig.getJumpActions()) {
            Utils.runActionDispatcher(action, player, player);
        }

        player.setVelocity(player.getLocation().getDirection().multiply(Math.min(settingsConfig.getLaunch(), 10D)).setY(Math.min(settingsConfig.getLaunchY(), 10D)));

        if (!canInfiniteJump) {
            user.setJumpAllowed(false);
        }

        user.setLastJumpTime(System.currentTimeMillis());
    }

    SettingsConfig settingsConfig = (SettingsConfig) ConfigManager.getConfigManager().getCustomConfig("settings.yml");

}
