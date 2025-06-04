package me.vesder.blazeydoublejump.config;

import me.vesder.blazeydoublejump.BlazeyDoubleJump;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

public class AdminMsgConfig {

    private final static AdminMsgConfig instance = new AdminMsgConfig();

    private File file;
    private YamlConfiguration config;

    private AdminMsgConfig() {

    }

    public void load() {

        file = new File(BlazeyDoubleJump.getPlugin().getDataFolder(), "admin-messages.yml");

        if (!file.exists()) {
            BlazeyDoubleJump.getPlugin().saveResource("admin-messages.yml", false);
        }

        config = new YamlConfiguration();

        try {
            config.load(file);
        } catch (Exception ignored) {

        }

    }

    public void save() {

        try {
            config.save(file);
        } catch (Exception ignored) {

        }

    }

    public void set(String path, Object value) {
        config.set(path, value);

        save();
    }

    public String getStringConfig(String path) {

        return config.getString(path);
    }

    public static AdminMsgConfig getAdminMsgConfig() {
        return instance;
    }
}
