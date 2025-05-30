package me.vesder.blazeydoublejump;

import me.vesder.blazeydoublejump.commands.CommandManager;
import me.vesder.blazeydoublejump.jumps.DoubleJump;
import me.vesder.blazeydoublejump.jumps.InfiniteJump;
import org.bukkit.plugin.java.JavaPlugin;

import static me.vesder.blazeydoublejump.utils.ConfigUtils.getBooleanConfig;
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

        getCommand("blazeydoublejump").setExecutor(new CommandManager());

        getLogger().info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= ★");
        getLogger().info("      BlazeyDoubleJump  ");
        getLogger().info(""); // Blank line for readability
        getLogger().info("      V:" + getDescription().getVersion());
        getLogger().info("      Made By @Vesder      ");
        getLogger().info("Contact Me In Discord For Support");
        getLogger().info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= ★");

    }

}