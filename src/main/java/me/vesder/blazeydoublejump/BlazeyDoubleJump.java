package me.vesder.blazeydoublejump;

import lombok.Getter;
import me.vesder.blazeydoublejump.commands.CommandManager;
import me.vesder.blazeydoublejump.config.ConfigManager;
import me.vesder.blazeydoublejump.listeners.JoinListener;
import me.vesder.blazeydoublejump.listeners.MoveListener;
import me.vesder.blazeydoublejump.listeners.ToggleFlightListener;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getPluginManager;

public final class BlazeyDoubleJump extends JavaPlugin {

    @Getter
    private static BlazeyDoubleJump plugin;

    @Override
    public void onEnable() {

        plugin = this;

        saveDefaultConfig();

        getPluginManager().registerEvents(new JoinListener(), this);
        getPluginManager().registerEvents(new MoveListener(), this);
        getPluginManager().registerEvents(new ToggleFlightListener(), this);

        getCommand("blazeydoublejump").setExecutor(new CommandManager());

        ConfigManager.getConfigManager().load();

        getLogger().info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= ★");
        getLogger().info("      BlazeyDoubleJump  ");
        getLogger().info(""); // Blank line for readability
        getLogger().info("      V:" + getDescription().getVersion());
        getLogger().info("      Made By @Vesder      ");
        getLogger().info("Contact Me In Discord For Support");
        getLogger().info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= ★");

    }

}