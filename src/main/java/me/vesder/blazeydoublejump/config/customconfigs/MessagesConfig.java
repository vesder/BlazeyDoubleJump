package me.vesder.blazeydoublejump.config.customconfigs;

import lombok.Getter;
import me.vesder.blazeydoublejump.config.ConfigUtils;
import me.vesder.blazeydoublejump.config.CustomConfig;

public class MessagesConfig extends CustomConfig {

    @Getter
    private String success;
    @Getter
    private String invalid_number;

    @Override
    public String getName() {
        return "messages.yml";
    }

    @Override
    public void loadValues() {
        success = ConfigUtils.getStringConfig(getName(), "success");
        invalid_number = ConfigUtils.getStringConfig(getName(), "invalid-number");
    }
}
