package me.vesder.blazeydoublejump.utils;

import org.bukkit.configuration.ConfigurationSection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static me.vesder.blazeydoublejump.BlazeyDoubleJump.getPlugin;

public class ConfigUtils {

    private static final Map<String, String> keyMapCache = new HashMap<>(); // lowercase -> actual path

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

//    public void configEditor() {
//
//    }

    public static Set<String> configReader(String path) {

        try {

            Object value = getPlugin().getConfig().get(findRealPath(path));

            if (!(value instanceof ConfigurationSection)) {
                return Collections.singleton(value.toString());
            }

            ConfigurationSection section = (ConfigurationSection) value;
            return section.getKeys(false);

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

}
