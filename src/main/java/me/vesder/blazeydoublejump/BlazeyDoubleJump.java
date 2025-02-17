package me.vesder.blazeydoublejump;

import me.vesder.blazeydoublejump.listeners.JoinListener;
import me.vesder.blazeydoublejump.listeners.MoveListener;
import org.bukkit.plugin.java.JavaPlugin;

import static me.vesder.blazeydoublejump.utils.TextUtils.*;
import static org.bukkit.Bukkit.getConsoleSender;
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

        if (getBooleanConfig("Settings.InfiniteJump")) {
            getPluginManager().registerEvents(new InfiniteJump(), this);
        } else {
            getPluginManager().registerEvents(new DoubleJump(), this);
            getPluginManager().registerEvents(new MoveListener(), this);
        }
        getPluginManager().registerEvents(new JoinListener(), this);

        getConsoleSender().sendMessage(color("&d=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= ★"));
        getConsoleSender().sendMessage(color("&d      BlazeyDoubleJump  "));
        getConsoleSender().sendMessage(""); // Blank line for readability
        getConsoleSender().sendMessage(color("&d      V:1.0    "));
        getConsoleSender().sendMessage(color("&d      Made By @Vesder      "));
        getConsoleSender().sendMessage(color("&dContact Me In Discord For Support"));
        getConsoleSender().sendMessage(color("&d=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= ★"));

    }

}