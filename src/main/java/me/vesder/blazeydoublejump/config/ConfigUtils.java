package me.vesder.blazeydoublejump.config;

import org.bukkit.configuration.ConfigurationSection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static me.vesder.blazeydoublejump.BlazeyDoubleJump.getPlugin;

public class ConfigUtils {

    private static final Map<String, String> keyMapCache = new HashMap<>(); // lowercase -> actual path
    private static final Map<String, Set<String>> configMapCache = new HashMap<>(); // path -> value

    public static void setConfig(String path, Object value) {

        getPlugin().getConfig().set(findRealPath(path), value);
        getPlugin().saveConfig();
    }

    private static String findRealPath(String path) {

        String lowerPath = path.toLowerCase();

        if (keyMapCache.containsKey(lowerPath)) {
            return keyMapCache.get(lowerPath);
        }

        for (String key : getPlugin().getConfig().getKeys(true)) {
            if (key.equalsIgnoreCase(path)) {
                keyMapCache.put(lowerPath, key);
                return key;
            }
        }

        throw new RuntimeException("Config path not found (ignoreCase): " + path);
    }

    public static Set<String> configReader(String path) {

        String lowerPath = path.toLowerCase();

        if (configMapCache.containsKey(lowerPath)) {
            return configMapCache.get(lowerPath);
        }

        try {

            Object value = getPlugin().getConfig().get(findRealPath(path));

            Set<String> result = value instanceof ConfigurationSection
                ? ((ConfigurationSection) value).getKeys(false)
                : Collections.singleton(value.toString());

            configMapCache.put(lowerPath, result);
            return result;

        } catch (RuntimeException ex) {

            return Collections.emptySet();
        }

    }

    public static String getStringConfig(String path) {

        return getPlugin().getConfig().getString(path);
    }

    public static double getDoubleConfig(String path) {

        return getPlugin().getConfig().getDouble(path);
    }

    public static double getDoubleConfig(String path, Double max) {

        return Math.min(getPlugin().getConfig().getDouble(path), max);
    }

    public static boolean getBooleanConfig(String path) {

        return getPlugin().getConfig().getBoolean(path);
    }

    // New Configuration System

    private static final ConfigManager configManager = ConfigManager.getConfigManager();

    public static String getStringConfig(String configName, String path) {

        return configManager.getCustomConfig(configName).config.getString(path);
    }

}
