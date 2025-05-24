package me.vesder.blazeydoublejump;

import me.vesder.blazeydoublejump.jumps.DoubleJump;
import me.vesder.blazeydoublejump.jumps.InfiniteJump;
import org.bukkit.plugin.java.JavaPlugin;

import static me.vesder.blazeydoublejump.utils.TextUtils.color;
import static me.vesder.blazeydoublejump.utils.TextUtils.getBooleanConfig;
import static org.bukkit.Bukkit.getPluginManager;

public final class BlazeyDoubleJump extends JavaPlugin {

    private static BlazeyDoubleJump plugin;

    public static BlazeyDoubleJump getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {

        plugin = this;

        saveDefaultConfig();

        getPluginManager().registerEvents(
            getBooleanConfig("Settings.InfiniteJump") ? new InfiniteJump() : new DoubleJump(), this);

        getLogger().info(color("&d=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= ★"));
        getLogger().info(color("&d      BlazeyDoubleJump  "));
        getLogger().info(""); // Blank line for readability
        getLogger().info(color("&d      V:" + getDescription().getVersion()));
        getLogger().info(color("&d      Made By @Vesder      "));
        getLogger().info(color("&dContact Me In Discord For Support"));
        getLogger().info(color("&d=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= ★"));

    }

}