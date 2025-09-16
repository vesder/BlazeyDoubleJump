package me.vesder.blazeydoublejump.config;

import org.bukkit.configuration.ConfigurationSection;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static me.vesder.blazeydoublejump.BlazeyDoubleJump.getPlugin;

public class ConfigUtils {

    private static final Map<String, String> keyMapCache = new HashMap<>(); // lowercase -> actual path
    private static final Map<String, Set<String>> configMapCache = new HashMap<>(); // path -> value

    public static void setConfig(String configName, String path, Object value) {

        configManager.getCustomConfig(configName).config.set(findRealPath(configName, path), value);
        getPlugin().saveConfig();
    }

    private static String findRealPath(String configName, String path) {

        String lowerPath = path.toLowerCase();

        if (keyMapCache.containsKey(lowerPath)) {
            return keyMapCache.get(lowerPath);
        }

        for (String key : configManager.getCustomConfig(configName).config.getKeys(true)) {
            if (key.equalsIgnoreCase(path)) {
                keyMapCache.put(lowerPath, key);
                return key;
            }
        }

        throw new RuntimeException("Config path not found (ignoreCase): " + path);
    }

    public static Set<String> configReader(String configName, String path) {

        String lowerPath = path.toLowerCase();

        if (configMapCache.containsKey(lowerPath)) {
            return configMapCache.get(lowerPath);
        }

        try {

            Object value = configManager.getCustomConfig(configName).config.get(findRealPath(configName, path));

            Set<String> result = value instanceof ConfigurationSection
                ? ((ConfigurationSection) value).getKeys(false)
                : Collections.singleton(value.toString());

            configMapCache.put(lowerPath, result);
            return result;

        } catch (RuntimeException ex) {

            return Collections.emptySet();
        }

    }

    // New Configuration System

    private static final ConfigManager configManager = ConfigManager.getConfigManager();

    public static String getStringConfig(String configName, String path) {

        return configManager.getCustomConfig(configName).config.getString(path);
    }

    public static List<String> getStringListConfig(String configName, String path) {

        return configManager.getCustomConfig(configName).config.getStringList(path);

    }

    public static boolean getBooleanConfig(String configName, String path) {

        return configManager.getCustomConfig(configName).config.getBoolean(path);
    }

    public static double getDoubleConfig(String configName, String path) {

        return configManager.getCustomConfig(configName).config.getDouble(path);
    }

}
