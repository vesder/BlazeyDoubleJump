package me.vesder.blazeydoublejump.config.customconfigs;

import lombok.Getter;
import me.vesder.blazeydoublejump.config.ConfigUtils;
import me.vesder.blazeydoublejump.config.CustomConfig;

import java.util.List;

public class SettingsConfig extends CustomConfig {

    // Settings
    @Getter
    String prefix;
    @Getter
    double launch;
    @Getter
    double launchY;
    @Getter
    double cooldown;
    // Jump
    @Getter
    private List<String> jumpActions;
    // Active
    @Getter
    private List<String> activeActions;
    // Errors
    @Getter
    private List<String> usedErrorActions;
    @Getter
    private List<String> cooldownErrorActions;
    @Getter
    private List<String> disabledErrorActions;


    @Override
    public String getName() {
        return "settings.yml";
    }

    @Override
    public void loadValues() {
        // Settings
        prefix = ConfigUtils.getStringConfig(getName(), "Settings.Prefix");
        launch = ConfigUtils.getDoubleConfig(getName(), "Settings.Launch");
        launchY = ConfigUtils.getDoubleConfig(getName(), "Settings.LaunchY");
        cooldown = ConfigUtils.getDoubleConfig(getName(), "Settings.Cooldown");
        // Jump
        jumpActions = ConfigUtils.getStringListConfig(getName(), "jump.actions");
        // Active
        activeActions = ConfigUtils.getStringListConfig(getName(), "active.actions");
        // Errors
        usedErrorActions = ConfigUtils.getStringListConfig(getName(), "errors.used.actions");
        cooldownErrorActions = ConfigUtils.getStringListConfig(getName(), "errors.cooldown.actions");
        disabledErrorActions = ConfigUtils.getStringListConfig(getName(), "errors.disabled.actions");
    }
}
