package me.vesder.blazeydoublejump;

import lombok.Getter;
import me.vesder.blazeydoublejump.commands.CommandManager;
import me.vesder.blazeydoublejump.config.ConfigManager;
import me.vesder.blazeydoublejump.hooks.MetricsLite;
import me.vesder.blazeydoublejump.hooks.UpdateChecker;
import me.vesder.blazeydoublejump.hooks.WorldGuardHook;
import me.vesder.blazeydoublejump.listeners.JoinListener;
import me.vesder.blazeydoublejump.listeners.MoveListener;
import me.vesder.blazeydoublejump.listeners.ToggleFlightListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getPluginManager;

public final class BlazeyDoubleJump extends JavaPlugin {

    public static final boolean isWorldGuardEnabled =
        Bukkit.getPluginManager().isPluginEnabled("WorldGuard");

    @Getter
    private static BlazeyDoubleJump plugin;

    @Override
    public void onLoad() {

        // Register the plugin instance
        plugin = this;

        if (isWorldGuardEnabled) {
            WorldGuardHook.init();
        } else {
            getLogger().info("WorldGuard not found! Some features may not work.");
        }

    }

    @Override
    public void onEnable() {

        // Load configs
        ConfigManager.getConfigManager().load();

        // Register events listeners
        getPluginManager().registerEvents(new JoinListener(), this);
        getPluginManager().registerEvents(new MoveListener(), this);
        getPluginManager().registerEvents(new ToggleFlightListener(), this);

        // Register commands
        getCommand("blazeydoublejump").setExecutor(new CommandManager());

        // Setup metrics with bStats
        int pluginId = 24830;
        MetricsLite metricsLite = new MetricsLite(this, pluginId);

        // Log plugin info to console
        getLogger().info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= ★");
        getLogger().info("      BlazeyDoubleJump  ");
        getLogger().info(""); // Blank line for readability
        getLogger().info("      V:" + getDescription().getVersion());
        getLogger().info("      Made By @Vesder      ");
        getLogger().info("Contact Me In Discord For Support");
        getLogger().info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= ★");

        // Check for plugin updates
        new UpdateChecker(this, 122651).getVersion(version -> {
            if (!getDescription().getVersion().equals(version)) {
                getLogger().warning(
                    "A new version is available! (Current: " + getDescription().getVersion() + ", Latest: " + version + ")"
                );
            }
        });

    }

}